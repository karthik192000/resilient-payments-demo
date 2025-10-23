package com.resilient.payments.demo.recon;

import com.resilient.payments.demo.adapter.PaymentsSwitchAdapter;
import com.resilient.payments.demo.entity.Payment;
import com.resilient.payments.demo.repository.PaymentsRepository;
import com.resilient.payments.demo.rest.api.response.PaymentsSwitchResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;
import java.util.Optional;


@Service
@Slf4j
public class PaymentsReconService {


    @Autowired
    PaymentsRepository paymentsRepository;

    @Autowired
    PaymentsSwitchAdapter paymentsSwitchAdapter;

    public void getAndUpdateFinalPaymentStatusFromSwitch(Long paymentId,String switchReference){
        Optional<Payment> paymentOptional = paymentsRepository.findById(paymentId);
        if(paymentOptional.isPresent()){
            Payment payment = paymentOptional.get();
            // Simulate fetching status from switch using switchReference
            PaymentsSwitchResponse paymentsSwitchResponse = paymentsSwitchAdapter.fetchFinalPaymentStatus(switchReference);
            if(Objects.nonNull(paymentsSwitchResponse)) {
                String status = paymentsSwitchResponse.getStatus();
                payment.setStatus(status);
                payment.setFinalstatusdt(Timestamp.from(Instant.now()));
                paymentsRepository.save(payment);
            }
        }
    }

}
