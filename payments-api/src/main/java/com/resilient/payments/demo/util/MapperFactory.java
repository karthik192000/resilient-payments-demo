package com.resilient.payments.demo.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MapperFactory {

  private static final class MapperHolder {
    private static final ObjectMapper mapper = new ObjectMapper();
  }

  public static ObjectMapper getMapperInstance() {
    return MapperHolder.mapper;
  }
}
