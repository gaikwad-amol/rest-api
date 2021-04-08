package com.someorg.rest.restapi.service;

import com.someorg.rest.restapi.model.Customer;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CustomerService {

  public List<Customer> retrieveAllCustomers() {
    Customer customer1 = Customer.builder()
      .customerId(1)
      .build();

    Customer customer2 = Customer.builder()
      .customerId(2)
      .build();

    return Arrays.asList(customer1, customer2);
  }

  public Integer addCustomer() {
    return 9;
  }

  public Customer getCustomer(Integer id) {
    return Customer.builder()
      .customerId(1)
      .build();
  }
}
