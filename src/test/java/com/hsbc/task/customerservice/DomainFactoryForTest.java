package com.hsbc.task.customerservice;

import com.hsbc.task.customerservice.domain.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

final public class DomainFactoryForTest {
  private DomainFactoryForTest(){}

  public static Transaction createTransaction() {
    return Transaction.builder()
        .dateTime(LocalDateTime.now())
        .amount(new BigDecimal("100.00"))
        .type(TransactionType.Credit)
        .build();
  }

  public static Transaction createTransactionWithMissingMandatoryAttributes() {
    return Transaction.builder()
      .dateTime(LocalDateTime.now())
      .type(TransactionType.Credit)
      .build();
  }

  public static Address createAddress() {
    return Address.builder()
      .line1("10 Garden city")
      .town("Wembley")
      .postcode("AB12 4LL")
      .country("UK")
      .build();
  }

  public static Address createAddressWithMissingMandatoryAttributes() {
    return Address.builder()
      .line1("10 Garden city")
      .town("wembley")
      .build();
  }

  public static Account createAccount() {
    return Account.builder()
      .accountId("12")
      .startDate(LocalDateTime.now())
      .transactions(createTransactions())
      .balance(new BigDecimal("1000.00"))
      .type(ProductType.Current)
      .build();
  }

  public static Account createAccountWithMissingMandatoryAttributes() {
    return Account.builder()
      .accountId("12")
      .startDate(LocalDateTime.now())
      .transactions(createTransactions())
      .type(ProductType.Current)
      .build();
  }

  public static Customer createCustomer() {
    return Customer.builder()
      .name("Victor Yao")
      .address(createAddress())
      .accounts(createAccounts())
      .dateOfBirth(LocalDate.now().minusYears(20))
      .build();
  }

  public static Customer createCustomerWithMissingMandatoryAttributes() {
    return Customer.builder()
      .name("Victor Yao")
      .address(createAddress())
      .accounts(createAccounts())
      .build();
  }


  private static List<Transaction> createTransactions() {
    return Arrays.asList(new Transaction[]{createTransaction()}.clone());
  }

  private static List<Account> createAccounts() {
    return Arrays.asList(new Account[]{createAccount()}.clone());
  }

}
