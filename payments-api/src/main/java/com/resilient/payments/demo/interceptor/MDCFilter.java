package com.resilient.payments.demo.interceptor;

import static com.resilient.payments.demo.constants.PaymentConstants.*;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class MDCFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    try {
      String correlationId = request.getHeader(CORRELATION_ID_HEADER);
      if (StringUtils.isBlank(correlationId)) {
        correlationId = CORRELATION_ID_PREFIX + System.currentTimeMillis();
      }
      MDC.put(CORRELATION_ID_LOG_KEY, correlationId);
      response.setHeader(CORRELATION_ID_HEADER, correlationId);
      filterChain.doFilter(request, response);
      System.out.println("MDCFilter - Correlation ID set to: " + correlationId);
    }
    finally {
      MDC.clear();
    }
  }


  protected boolean shouldNotFilter(HttpServletRequest request){
    String path = request.getRequestURI();
    return SKIP_FILTER_URIS.stream().anyMatch(path :: startsWith);
  }
}
