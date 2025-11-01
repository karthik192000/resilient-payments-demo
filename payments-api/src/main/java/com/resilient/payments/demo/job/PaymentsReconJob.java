package com.resilient.payments.demo.job;

import com.resilient.payments.demo.recon.PaymentsReconService;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import org.jobrunr.jobs.JobId;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/** * Job class for scheduling payment reconciliation tasks. */
@Component
public class PaymentsReconJob {

  @Autowired JobScheduler jobScheduler;

  @Value("${payment.recon.job.delay.seconds:10}")
  private Long paymentReconJobDelaySeconds;

  @Autowired private PaymentsReconService paymentsReconService;

  /**
   * Schedules a reconciliation job to fetch and update the final payment status from the Payments
   * Switch.
   *
   * @param paymentId
   * @param switchReference
   * @return
   */
  public String enqueueJob(Long paymentId, String switchReference) {
    JobId jobId =
        jobScheduler.schedule(
            Instant.now().plus(paymentReconJobDelaySeconds, ChronoUnit.SECONDS),
            () ->
                paymentsReconService.getAndUpdateFinalPaymentStatusFromSwitch(
                    paymentId, switchReference));
    return jobId.toString();
  }
}
