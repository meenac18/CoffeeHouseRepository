package com.example.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.ws.rs.QueryParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.OrderDTO;
import com.example.dto.Product;
import com.example.dto.Receipt;
import com.example.service.CoffeeHouseService;
import com.example.vo.Coffee;
import com.example.vo.Customer;
import com.example.vo.Order;
import com.google.common.base.Stopwatch;

@RestController
@RequestMapping("/coffeehouse")
@ExposesResourceFor(Receipt.class)
public class CoffeeHouseController {
	
	@Autowired
	CoffeeHouseService service;
	
	//@Autowired
	//private EntityLinks entityLinks;
	private static final Logger logger = LoggerFactory.getLogger(CoffeeHouseController.class);
	
    
	@GetMapping(value="/searchcustomer", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> searchCustomer(@QueryParam(value = "customerName") String customerName,@QueryParam(value = "mobileNumber") String mobileNumber )
	{
		Stopwatch timer = Stopwatch.createStarted();
		logger.info("*****Entered searchcustomer******");
		Customer c=service.searchCustomer(customerName,mobileNumber);
		logger.info("*****Exiting searchcustomer******"+timer.stop());
		return ResponseEntity.ok().body(c);
		
	}
	
	@PostMapping(value="/createcustomer")
	public ResponseEntity createCustomer(@Valid @RequestBody Customer cust,Errors errors) {
		Stopwatch timer = Stopwatch.createStarted();
		logger.info("*****Entered createcustomer******");
		
		if (errors.hasErrors()) {

			// get all errors 
            String str=(errors.getAllErrors()
				.stream()
				.map(x -> x.getDefaultMessage())
				.collect(Collectors.joining(",")));
				
            return ResponseEntity.badRequest().body(str);

        }
		long id=service.createCustomer(cust);
		System.out.println("hello"+id);
		logger.info("*****Exiting createcustomer******"+timer.stop());
		return ResponseEntity.ok().body("New Customer has been added with ID:" + id);
		
		
	}
	
	@PostMapping(value="/createcoffee")
	public ResponseEntity createcoffee(@Valid @RequestBody Coffee coffee,Errors errors) {
		Stopwatch timer = Stopwatch.createStarted();
		logger.info("*****Entered createcoffee******");
		
		if (errors.hasErrors()) {

			// get all errors 
            String str=(errors.getAllErrors()
				.stream()
				.map(x -> x.getDefaultMessage())
				.collect(Collectors.joining(",")));
				
            return ResponseEntity.badRequest().body(str);

        }
		long id=service.createCoffee(coffee);
		logger.info("*****Exiting createcoffee******"+timer.stop());
		return ResponseEntity.ok().body("New Coffee has been added with ID:" + id);
		
		
	}
	
	@GetMapping(value="/listcoffee", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Coffee>> listCoffee()
	{
		Stopwatch timer = Stopwatch.createStarted();
		logger.info("*****Entered listcoffee******");
		List<Coffee>c=service.listCoffee();
		logger.info("*****Exiting listcoffee******"+timer.stop());
		return ResponseEntity.ok().body(c);
		
	}
	
	@PostMapping(value="/orderitems")
	public ResponseEntity orderitems(@RequestBody OrderDTO orderdto) {
		Stopwatch timer = Stopwatch.createStarted();
		logger.info("*****Entered orderitems******");
		long id=0;
		Order newOrder;
		try {
			
		       
			id=service.saveOrder(orderdto);
		
		
		
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		logger.info("*****Exiting orderitems******"+timer.stop());
		return ResponseEntity.ok().body("New Order has been added with ID:" + id);
	}
	
	@GetMapping(value="/receipt", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Receipt> receipt(@RequestParam(value="orderid") long orderid)
	{
		Stopwatch timer = Stopwatch.createStarted();
		logger.info("*****Entered orderitems******");
		Receipt r=service.receipt(orderid);
		return ResponseEntity.ok().body(r);
		
	}
	
	@GetMapping(value="/report",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product>> report(@RequestParam("rptDt") String dt)
	{
		Stopwatch timer = Stopwatch.createStarted();
		logger.info("*****Entered report******");
		return ResponseEntity.ok().body(service.report(dt));
	}
	
		

}
