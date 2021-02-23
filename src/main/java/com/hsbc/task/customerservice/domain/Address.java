package com.hsbc.task.customerservice.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class Address {
  @NonNull
  private final String line1;
  private final String line2;
  @NonNull
  private final String town;
  private final String county;
  @NonNull
  private final String postcode;
  @NonNull
  private final String country;
}
