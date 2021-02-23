package com.hsbc.task.customerservice.controller;

import com.hsbc.task.customerservice.Util;
import com.hsbc.task.customerservice.domain.Account;
import com.hsbc.task.customerservice.domain.Customer;
import com.hsbc.task.customerservice.exception.ResourceNotFoundException;
import com.hsbc.task.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {

  @Autowired
  private CustomerRepository repository;

  @GetMapping("/{name}")
  public ResponseEntity<Customer> getByName(@PathVariable(value = "name") String name) {
    Util.validate(name, "Customer name");
    Optional<Customer> optional = this.repository.findByName(name);
    if(optional.isEmpty()) {
      throw new ResourceNotFoundException("No customer found for name:" + name);
    }
    return  ResponseEntity.ok().body(optional.get());
  }

  @GetMapping("/{name}/{accountId}")
  public ResponseEntity<BigDecimal> getByName(@PathVariable(value = "name") String name,
                                              @PathVariable(value = "accountId") String accountId) {
    Util.validate(name, "Customer name");
    Util.validate(accountId, "Customer accountId");
    Customer customer = this.repository.findByName(name)
      .orElseThrow( () -> new ResourceNotFoundException("No customer found for name:" + name));

    BigDecimal balance = customer.getAccounts().stream()
      .filter(a -> accountId.equals(a.getAccountId()))
      .map(Account::getBalance)
      .findFirst().orElseThrow(() ->
        new ResourceNotFoundException(String.format("Invalid account: %s for customer: %s",
          name, accountId)));

    return  ResponseEntity.ok().body(balance);
  }


  @PostMapping()
  public ResponseEntity<String> createCustomer(@RequestBody Customer customer) {
    Objects.requireNonNull(customer, "Customer must be set");
    String name = customer.getName();
    if(this.repository.existsByName(name)) {
      throw new IllegalArgumentException(String.format("Customer by name: %s already exists.", name));
    }
    this.repository.save(customer);
    return ResponseEntity.ok().body(name);
  }

  @PostMapping("/{name}")
  public ResponseEntity<Boolean> addAccount(@PathVariable(value = "name") String name,
                                            @RequestBody Account account) {
    Util.validate(name, "Customer name");
    Objects.requireNonNull(account, "Account must be set");
    Customer customer = this.repository.findByName(name)
      .orElseThrow( () -> new ResourceNotFoundException("No customer found for name:" + name));
    String accountId = account.getAccountId();
    Optional<Account> optional = customer.getAccounts().stream()
      .filter(a -> a.getAccountId().equals(accountId))
      .findFirst();
    if(optional.isPresent()) {
      throw new IllegalArgumentException(
        String.format("Account already exists by the accountId: %s for the customer: %s", accountId, name));
    }
    customer.getAccounts().add(account);
    this.repository.save(customer);
    return ResponseEntity.ok().body(true);
  }


}
