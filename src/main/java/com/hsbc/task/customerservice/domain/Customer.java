package com.hsbc.task.customerservice.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class Customer {
  @NonNull
  private final String name;
  @NonNull
  private final LocalDate dateOfBirth;
  @NonNull
  private final Address address;
  @NonNull
  private final List<Account> accounts;
}
