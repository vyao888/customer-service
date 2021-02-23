package com.hsbc.task.customerservice.domain;

import com.hsbc.task.customerservice.DomainFactoryForTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccountTest {
  @Test
  public void constructAccountOk() {
    Account a = DomainFactoryForTest.createAccount();
    Assertions.assertNotNull(a);
  }

  @Test
  public void constructAccountFail() {
    Assertions.assertThrows(NullPointerException.class,
        DomainFactoryForTest::createAccountWithMissingMandatoryAttributes);
  }

}
