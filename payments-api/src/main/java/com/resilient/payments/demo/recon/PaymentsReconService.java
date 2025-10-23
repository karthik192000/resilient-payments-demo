package com.resilient.payments.demo.recon;

import com.resilient.payments.demo.adapter.PaymentsSwitchAdapter;
import com.resilient.payments.demo.dao.PaymentsDao;
import com.resilient.payments.demo.entity.Payment;
import com.resilient.payments.demo.rest.api.response.PaymentsSwitchResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;


/**
 * * Service class for reconciling payment statuses with the Payments Switch.
 */
@Service
@Slf4j
public class PaymentsReconService {


    @Autowired
    PaymentsDao paymentsDao;

    @Autowired
    PaymentsSwitchAdapter paymentsSwitchAdapter;

    /**
     * Fetches the final payment status from the Payments Switch using the provided switch reference
     * This method is invoked by the reconciliation job to update payment statuses.
     * @param paymentId
     * @param switchReference
     */
    public void getAndUpdateFinalPaymentStatusFromSwitch(Long paymentId,String switchReference){
        Payment payment = paymentsDao.getPayment(paymentId);
        // Simulate fetching status from switch using switchReference
        PaymentsSwitchResponse paymentsSwitchResponse = paymentsSwitchAdapter.fetchFinalPaymentStatus(switchReference);
        if (Objects.nonNull(paymentsSwitchResponse)) {
            String status = paymentsSwitchResponse.getStatus();
            payment.setStatus(status);
            payment.setFinalstatusdt(Timestamp.from(Instant.now()));
            paymentsDao.updatePayment(payment);
        }
    }

}
