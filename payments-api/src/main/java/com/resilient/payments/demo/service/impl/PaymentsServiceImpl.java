package com.resilient.payments.demo.service.impl;

import com.resilient.payments.demo.adapter.PaymentsSwitchAdapter;
import com.resilient.payments.demo.dao.PaymentsDao;
import com.resilient.payments.demo.entity.Payment;
import com.resilient.payments.demo.enums.PaymentStatus;
import com.resilient.payments.demo.job.PaymentsReconJob;
import com.resilient.payments.demo.mappers.PaymentsMapper;
import com.resilient.payments.demo.mappers.PaymentsSwitchMapper;
import com.resilient.payments.demo.rest.api.request.PaymentRequest;
import com.resilient.payments.demo.rest.api.request.PaymentsSwitchRequest;
import com.resilient.payments.demo.rest.api.response.PaymentResponse;
import com.resilient.payments.demo.rest.api.response.PaymentsSwitchResponse;
import com.resilient.payments.demo.service.PaymentsService;
import com.resilient.payments.demo.util.PaymentsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

import static com.resilient.payments.demo.constants.PaymentConstants.SUCCESS;

@Service
@Slf4j
public class PaymentsServiceImpl implements PaymentsService {

    @Autowired
    PaymentsDao paymentsDao;

    @Autowired
    PaymentsMapper paymentsMapper;

    @Autowired
    PaymentsSwitchMapper paymentsSwitchMapper;

    @Autowired
    PaymentsSwitchAdapter paymentsSwitchAdapter;
    @Autowired
    PaymentsReconJob paymentsReconJob;

    @Override
    public PaymentResponse execute(PaymentRequest paymentRequest) {
        log.info("PaymentsServiceImpl.execute called with request: {}", paymentRequest);

        try {
            Payment payment = preparePayment(paymentRequest);
            Payment savedPayment = paymentsDao.createPayment(payment);
            PaymentsSwitchResponse switchResponse = executeSwitch(paymentRequest);
            Payment updatedPayment = updatePaymentWithSwitchResponse(savedPayment, switchResponse);
            if (switchResponse.isRecon()) {
                enqueueReconciliationJob(updatedPayment, switchResponse.getSwitchReference());
            }
            return paymentsMapper.map(updatedPayment);
        }
        catch (Exception ex) {
            log.error("Exception in PaymentsServiceImpl.execute: ", ex);
            return null;
        }
    }


    private Payment preparePayment(PaymentRequest paymentRequest) {
        Payment payment = paymentsMapper.map(paymentRequest);
        payment.setPaymentReference(PaymentsUtil.generatePaymentReference());
        payment.setStatus(PaymentStatus.RECEIVED.getStatus());
        payment.setCreatedDt(Timestamp.from(Instant.now()));
        return payment;
    }

    private PaymentsSwitchResponse executeSwitch(PaymentRequest paymentRequest) {
        PaymentsSwitchRequest switchRequest = paymentsSwitchMapper.map(paymentRequest);
        return paymentsSwitchAdapter.executePayment(switchRequest);
    }

    private Payment updatePaymentWithSwitchResponse(Payment payment, PaymentsSwitchResponse response) {
        String status = response.getStatus().equals(SUCCESS)
                ? PaymentStatus.COMPLETED.getStatus()
                : (!response.isRecon() ? PaymentStatus.FAILED.getStatus() : PaymentStatus.PENDING.getStatus());

        payment.setStatus(status);
        payment.setUpdatedDt(Timestamp.from(Instant.now()));
        payment.setSwitchReference(response.getSwitchReference());

        return paymentsDao.updatePayment(payment);
    }

    private void enqueueReconciliationJob(Payment payment, String switchReference) {
        try {
            String jobId = paymentsReconJob.enqueueJob(payment.getPaymentId(), switchReference);
            payment.setReconjobid(jobId);
        } catch (Exception e) {
            log.error("Failed to enqueue reconciliation job for paymentId: {}", payment.getPaymentId(), e);
        }
    }

}
