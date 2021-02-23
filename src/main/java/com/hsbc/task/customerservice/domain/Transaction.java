package com.hsbc.task.customerservice.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class Transaction {
  @NonNull
  private final TransactionType type;
  @NonNull
  private final LocalDateTime dateTime;
  @NonNull
  private final BigDecimal amount;
}
