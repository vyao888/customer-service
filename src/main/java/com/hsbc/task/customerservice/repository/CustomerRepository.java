package com.hsbc.task.customerservice.repository;

import com.hsbc.task.customerservice.domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
  Optional<Customer> findByName(String name);
  boolean existsByName(String name);
  void deleteByName(String name);
}
