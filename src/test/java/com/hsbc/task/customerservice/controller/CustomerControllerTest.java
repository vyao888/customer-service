package com.hsbc.task.customerservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hsbc.task.customerservice.DomainFactoryForTest;
import com.hsbc.task.customerservice.domain.Account;
import com.hsbc.task.customerservice.domain.Customer;
import com.hsbc.task.customerservice.domain.DomainTestHelper;
import com.hsbc.task.customerservice.exception.GlobalExceptionHandler;
import com.hsbc.task.customerservice.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Slf4j
public class CustomerControllerTest {
  private DomainTestHelper helper;

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper mapper;
  @Autowired
  private CustomerRepository repository;

  private final Customer customer = DomainFactoryForTest.createCustomer();

  @Before
  public void setUp() {
    this.helper = new DomainTestHelper(this.mapper);
  }

  @After
  public void tearDown() {
    this.helper = null;
    this.repository.deleteByName(this.customer.getName());
  }

  @Test
  public void findCustomerByName() throws Exception {
    if(!this.repository.existsByName(this.customer.getName())) {
      this.repository.save(customer);
    }
    String content = this.mockMvc.perform(get("/customers/" + this.customer.getName())
      .contentType(APPLICATION_JSON)
      .content(this.helper.toJson(this.customer)))
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn().getResponse().getContentAsString();
    assertTrue(content.contains(this.customer.getName()));
    assertTrue(content.contains(this.customer.getAddress().getCountry()));
  }

  @Test
  public void findNonExistingCustomerByName() throws Exception {
    String content = this.mockMvc.perform(get("/customers/" + this.customer.getName())
      .contentType(APPLICATION_JSON)
      .content(this.helper.toJson(this.customer)))
      .andDo(print())
      .andExpect(status().isNotFound())
      .andReturn().getResponse().getContentAsString();
    assertTrue(content.contains(GlobalExceptionHandler.NOT_FOUND));
  }

  @Test
  public void getAccountBalanceByName() throws Exception {
    if(!this.repository.existsByName(this.customer.getName())) {
      this.repository.save(customer);
    }
    String accountId = this.customer.getAccounts().get(0).getAccountId();
    String url = String.format("/customers/%s/%s", this.customer.getName(), accountId);
    String content = this.mockMvc.perform(get(url)
      .contentType(APPLICATION_JSON)
      .content(this.helper.toJson(this.customer)))
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn().getResponse().getContentAsString();

    String balance = this.customer.getAccounts().get(0).getBalance().toString();
    assertTrue(content.contains(balance));
  }

  @Test
  public void createCustomerByName() throws Exception {
    if(this.repository.existsByName(this.customer.getName())) {
      this.repository.deleteByName(this.customer.getName());
    }
    String content = this.mockMvc.perform(post("/customers")
      .contentType(APPLICATION_JSON)
      .content(this.helper.toJson(this.customer)))
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn().getResponse().getContentAsString();
    log.info(content);
    assertTrue(content.contains(this.customer.getName()));
  }

  @Test
  public void addAccount() throws Exception {
    if(!this.repository.existsByName(this.customer.getName())) {
      this.repository.save(customer);
    }
    Account account = DomainFactoryForTest.createSavingAccount();
    String content = this.mockMvc.perform(post("/customers/" + this.customer.getName())
      .contentType(APPLICATION_JSON)
      .content(this.helper.toJson(account)))
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn().getResponse().getContentAsString();
    assertTrue(content.contains("true"));
    Customer c = this.repository.findByName(this.customer.getName()).get();
    long count = c.getAccounts()
      .stream()
      .filter(a -> a.getAccountId().equals(account.getAccountId()))
      .count();

    Assertions.assertEquals(1, count);
  }

}
