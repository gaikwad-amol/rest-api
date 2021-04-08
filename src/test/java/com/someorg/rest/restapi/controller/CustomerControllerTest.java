package com.someorg.rest.restapi.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.someorg.rest.restapi.model.Customer;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {TestSetUpConfig.class},
  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "classpath:application-test.properties")
class CustomerControllerTest {

  private MockMvc mockMvc;

  @Autowired
  private WebApplicationContext webApplicationContext;
  private final ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

  @Test
  public void shouldGetAllCustomers() throws Exception {

    this.mockMvc = MockMvcBuilders
      .webAppContextSetup(webApplicationContext)
      .build();

    mockMvc.perform(MockMvcRequestBuilders
      .get("/customers")
      .accept(MediaType.APPLICATION_JSON))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("[*].customerId").isNotEmpty())
      .andDo(mvcResult -> {
        String json = mvcResult.getResponse().getContentAsString();
        System.out.println("JSON: " + json);
        final Customer[] actualCustomers = objectMapper.readValue(json, Customer[].class);
        System.out.println("Size of the customer list: " + actualCustomers.length);
        assertThat(actualCustomers.length, Matchers.is(2));
      });
  }

  @Test
  public void shouldGetCustomer() throws Exception {

    this.mockMvc = MockMvcBuilders
      .webAppContextSetup(webApplicationContext)
      .build();

    mockMvc.perform(MockMvcRequestBuilders
      .get("/customers/1")
      .accept(MediaType.APPLICATION_JSON))
      .andDo(print())
      .andExpect(status().isOk())
      .andDo(mvcResult -> {
        String json = mvcResult.getResponse().getContentAsString();
        System.out.println("JSON: " + json);
      });
  }

}