package com.hsbc.task.customerservice.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class Account {
  @NonNull
  private final String accountId;
  @NonNull
  private final LocalDateTime startDate;
  @NonNull
  private final BigDecimal balance;
  private final List<Transaction> transactions;
  @NonNull
  private final ProductType type;

}
