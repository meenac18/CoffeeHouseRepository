package com.example.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.dao.CustomerRepository;
import com.example.demo.DemoApplicationTests;
import com.example.service.CoffeeHouseService;
import com.example.vo.Coffee;
import com.example.vo.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class})
public class CoffeeHouseControllerTest extends DemoApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@MockBean
    private CoffeeHouseService service;
	
	@MockBean 
	private CustomerRepository customerRepository;


	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		}

	
	@Test
	public void testCustomerCreation() throws Exception {
		
		Customer cust=new Customer();
		 when(
				 service.createCustomer(any(Customer.class))
		        ).thenReturn(
		                (long)1
		        );
		cust.setCname("Vidya");
		cust.setMblnumber("1234567890");
		cust.setCustomerid(null);
		 mockMvc.perform(post("/coffeehouse/createcustomer").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(cust)))
				.andExpect(status().isOk());

	}
	
	@Test
	public void testCoffeeCreation() throws Exception {
		
		Coffee coffee=new Coffee();
		 when(
				 service.createCustomer(any(Customer.class))
		        ).thenReturn(
		                (long)1
		        );
		coffee.setCoffeeName("Espresso");
		coffee.setDesc("Espresso Desc");
		coffee.setPrice(25);
		coffee.setServes(100);
		 mockMvc.perform(post("/coffeehouse/createcoffee").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(coffee)))
				.andExpect(status().isOk());

	}
	
	public static String asJsonString(final Object obj) {
	    try {
	    	
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
