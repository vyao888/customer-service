package com.hsbc.task.customerservice.domain;

import com.hsbc.task.customerservice.DomainFactoryForTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TransactionTest {
  @Test
  public void constructTransactionOk() {
    Transaction t = DomainFactoryForTest.createTransaction();
    Assertions.assertNotNull(t);
  }

  @Test
  public void constructTransactionFail() {
    Assertions.assertThrows(NullPointerException.class,
        DomainFactoryForTest::createTransactionWithMissingMandatoryAttributes);
  }

}
