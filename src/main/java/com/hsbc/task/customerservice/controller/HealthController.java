package com.hsbc.task.customerservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
  @GetMapping("/")
  public ResponseEntity<String> homeOk() {
    return  ResponseEntity.ok().body("Welcome!");
  }
}
