package com.resilient.payments.demo.job;

import com.resilient.payments.demo.recon.PaymentsReconService;
import org.jobrunr.jobs.JobId;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;


@Component
public class PaymentsReconJob {


    @Autowired
    JobScheduler jobScheduler;


    @Value("${payment.recon.job.delay.seconds:10}")
    private Long paymentReconJobDelaySeconds;

    @Autowired
    private PaymentsReconService paymentsReconService;


    public String enqueueJob(Long paymentId, String switchReference){
        JobId jobId =  jobScheduler.schedule(Instant.now().plus(paymentReconJobDelaySeconds, ChronoUnit.SECONDS),() -> paymentsReconService.getAndUpdateFinalPaymentStatusFromSwitch(paymentId,switchReference));
        return jobId.toString();
    }
}
