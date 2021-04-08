package com.someorg.rest.restapi.controller;

import com.someorg.rest.restapi.model.Customer;
import com.someorg.rest.restapi.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class CustomerController {

  private final CustomerService customerService;

  @Autowired
  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @RequestMapping(value = "/customers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Customer>> getCustomers() {
    return new ResponseEntity<>(customerService.retrieveAllCustomers(), HttpStatus.OK);
  }

  @RequestMapping(value = "/customers/customer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Integer> addCustomer() {
    return new ResponseEntity<>(customerService.addCustomer(), HttpStatus.OK);
  }

  @RequestMapping(value = "/customers/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Customer> getCustomer(@PathVariable Integer id) {
    return new ResponseEntity<>(customerService.getCustomer(id), HttpStatus.OK);
  }
}
