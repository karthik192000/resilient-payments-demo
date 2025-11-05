package com.resilient.payments.demo.interceptor;

import static com.resilient.payments.demo.constants.PaymentConstants.*;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@Slf4j
public class MDCFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    try {
      log.debug("MDCFilter.doFilterInternal called for URI: {}", request.getRequestURI());
      populateCorrelationId(request, response);
      populatePartnerId(request, response);
      filterChain.doFilter(request, response);
      log.debug("MDCFilter.doFilterInternal completed for URI: {}", request.getRequestURI());
    } finally {
      MDC.clear();
    }
  }

  protected boolean shouldNotFilter(HttpServletRequest request) {
    String path = request.getRequestURI();
    return SKIP_FILTER_URIS.stream().anyMatch(path::startsWith);
  }

  protected void populateCorrelationId(HttpServletRequest request, HttpServletResponse response) {
    log.debug("MDCFilter.populateCorrelationId called");
    String correlationId = request.getHeader(CORRELATION_ID_HEADER);
    if (StringUtils.isBlank(correlationId)) {
      correlationId = CORRELATION_ID_PREFIX + System.currentTimeMillis();
    }
    MDC.put(CORRELATION_ID_LOG_KEY, correlationId);
    response.setHeader(CORRELATION_ID_HEADER, correlationId);
    log.debug("MDCFilter.populateCorrelationId set correlationId: {}", correlationId);
  }

  protected void populatePartnerId(HttpServletRequest request, HttpServletResponse response) {
    log.debug("MDCFilter.populatePartnerId called");
    String partnerId = request.getHeader(PARTNER_ID_HEADER);
    if (StringUtils.isNotBlank(partnerId)) {
      MDC.put(PARTNER_ID_LOG_KEY, partnerId);
    }
    response.setHeader(PARTNER_ID_HEADER, partnerId);
    log.debug("MDCFilter.populatePartnerId set partnerId: {}", partnerId);
  }
}
