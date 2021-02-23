package com.hsbc.task.customerservice.repository;

import com.hsbc.task.customerservice.DomainFactoryForTest;
import com.hsbc.task.customerservice.domain.Customer;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomerRepositoryTest {

  @Autowired
  private CustomerRepository repository;

  private final Customer customer = DomainFactoryForTest.createCustomer();

  @Test
  public void aInsertShouldBeOk() {
    Customer c = this.repository.save(customer);
    Assertions.assertNotNull(c);
    Assertions.assertEquals(this.customer.getName(), c.getName());
  }

  @Test
  public void bFindByIdOk() {
    Assertions.assertTrue(this.repository.existsByName(this.customer.getName()));
  }

  @Test
  public void cDeletionOk() {
    Optional<Customer> optional = this.repository.findByName(customer.getName());
    if(optional.isPresent()) {
      this.repository.deleteByName(customer.getName());
      Assertions.assertFalse(this.repository.existsByName(customer.getName()));
    } else {
      Assertions.fail();
    }
  }

}
