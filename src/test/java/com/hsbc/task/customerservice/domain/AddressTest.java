package com.hsbc.task.customerservice.domain;

import com.hsbc.task.customerservice.DomainFactoryForTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddressTest {
  @Test
  public void constructAddressOk() {
    Address a = DomainFactoryForTest.createAddress();
    Assertions.assertNotNull(a);
  }

  @Test
  public void constructAddressFail() {
    Assertions.assertThrows(NullPointerException.class,
        DomainFactoryForTest::createAddressWithMissingMandatoryAttributes);
  }

}
