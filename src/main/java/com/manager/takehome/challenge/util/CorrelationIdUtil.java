package com.manager.takehome.challenge.util;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;
import javax.servlet.http.HttpServletRequest;

public class CorrelationIdUtil {
  /**
   * retrieve correlationId from the header else generate new
   * * @return String.
   */
  public static String getCorrelationIdFromHeader(final HttpServletRequest request) {
    String correlationId = request.getHeader(Constants.CORRELATION_ID);
    if (StringUtils.isBlank(correlationId)) {
      correlationId = generateUniqueCorrelationId();
    }
    return correlationId;
  }

  public static String generateUniqueCorrelationId() {
    return UUID.randomUUID().toString();
  }
}
