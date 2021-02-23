package com.hsbc.task.customerservice.domain;

import com.hsbc.task.customerservice.DomainFactoryForTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomerTest {
  @Test
  public void constructCustomerOk() {
    Customer c = DomainFactoryForTest.createCustomer();
    Assertions.assertNotNull(c);
  }

  @Test
  public void constructCustomerFail() {
    Assertions.assertThrows(NullPointerException.class,
        DomainFactoryForTest::createCustomerWithMissingMandatoryAttributes);
  }

}
