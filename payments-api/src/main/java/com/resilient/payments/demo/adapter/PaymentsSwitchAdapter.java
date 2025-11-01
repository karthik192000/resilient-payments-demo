package com.resilient.payments.demo.adapter;

import static com.resilient.payments.demo.constants.PaymentConstants.SWITCH_REFERENCE;

import com.resilient.payments.demo.enums.PaymentStatus;
import com.resilient.payments.demo.resilience.PaymentsRetryPredicate;
import com.resilient.payments.demo.rest.api.request.PaymentsSwitchRequest;
import com.resilient.payments.demo.rest.api.response.PaymentsSwitchResponse;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/** Adapter class to interact with the Payments Switch API. */
@Component
@Slf4j
public class PaymentsSwitchAdapter {

  @Autowired
  @Qualifier("defaultRestTemplate")
  private RestTemplate restTemplate;

  @Value("${api.payments.switch}")
  private String paymentsSwitchAPI;

  @Value("${api.payments.final-status}")
  private String paymentsFinalStatusAPI;

  private static final PaymentsRetryPredicate paymentsRetryPredicate = new PaymentsRetryPredicate();

  /**
   * Executes a payment by calling the Payments Switch API. Retries on failure based on the defined
   * retry policy. Includes fallback logic when all retry attempts are exhausted.
   *
   * @param paymentsSwitchRequest
   * @return
   */
  @Retry(name = "payments-switch", fallbackMethod = "fallbackOnRetryFailure")
  public PaymentsSwitchResponse executePayment(PaymentsSwitchRequest paymentsSwitchRequest) {
    PaymentsSwitchResponse paymentsSwitchResponse = null;
    HttpEntity<?> entity = new HttpEntity<>(paymentsSwitchRequest);
    try {
      ResponseEntity<PaymentsSwitchResponse> response =
          restTemplate.exchange(
              paymentsSwitchAPI, HttpMethod.POST, entity, PaymentsSwitchResponse.class);
      if (response.getStatusCode().is2xxSuccessful()) {
        paymentsSwitchResponse = response.getBody();
      }
    } catch (RestClientResponseException ex) {
      log.error("RestClientResponseException in PaymentsSwitchAdapter.executePayment: ", ex);
      HttpHeaders headers = ex.getResponseHeaders();
      String switchReference = headers.getFirst(SWITCH_REFERENCE);
      paymentsSwitchRequest.setSwitchReference(switchReference);
      throw ex;
    } catch (Exception ex) {
      log.error("Exception in PaymentsSwitchAdapter.executePayment: ", ex);
      throw ex;
    }
    return paymentsSwitchResponse;
  }

  /**
   * Fallback method invoked when all retry attempts are exhausted.
   *
   * @param paymentsSwitchRequest
   * @param t
   * @return
   */
  protected PaymentsSwitchResponse fallbackOnRetryFailure(
      PaymentsSwitchRequest paymentsSwitchRequest, Throwable t) {
    log.error(
        "PaymentsSwitchAdapter.callPaymentsSwitch finally failed with switchReference: {}. Exception: {}",
        paymentsSwitchRequest.getSwitchReference(),
        t.getMessage());
    PaymentsSwitchResponse paymentsSwitchResponse = new PaymentsSwitchResponse();
    paymentsSwitchResponse.setStatus(PaymentStatus.FAILED.getStatus());
    paymentsSwitchResponse.setSwitchReference(paymentsSwitchRequest.getSwitchReference());
    paymentsSwitchResponse.setRecon(shouldScheduleRecon(t));
    return paymentsSwitchResponse;
  }

  /**
   * Determines whether to schedule reconciliation based on the type of exception encountered.
   *
   * @param t
   * @return
   */
  private boolean shouldScheduleRecon(Throwable t) {
    return paymentsRetryPredicate.test(t);
  }

  /**
   * Fetches the final payment status from the Payments Switch using the provided switch reference.
   *
   * @param switchReference
   * @return
   */
  public PaymentsSwitchResponse fetchFinalPaymentStatus(String switchReference) {
    log.debug("Fetching final payment status from switch for switchReference: {}", switchReference);
    PaymentsSwitchResponse paymentsSwitchResponse = null;
    try {
      String finalStatusUrl =
          UriComponentsBuilder.fromUriString(paymentsFinalStatusAPI)
              .buildAndExpand(switchReference)
              .toUriString();
      ResponseEntity<PaymentsSwitchResponse> response =
          restTemplate.exchange(finalStatusUrl, HttpMethod.GET, null, PaymentsSwitchResponse.class);
      if (response.getStatusCode().is2xxSuccessful()) {
        paymentsSwitchResponse = response.getBody();
      }
    } catch (Exception ex) {
      log.error("Exception in PaymentsSwitchAdapter.fetchFinalPaymentStatus: ", ex);
    }
    return paymentsSwitchResponse;
  }
}
