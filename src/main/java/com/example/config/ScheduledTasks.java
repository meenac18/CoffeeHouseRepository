package com.example.config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.dao.CoffeeRepository;
import com.example.dao.OrderDetailsRepository;
import com.example.dto.Product;
import com.example.dto.ReportDTO;
import com.example.vo.Coffee;
import com.example.vo.OrderDetails;

@Component
public class ScheduledTasks {

	@Autowired
	CoffeeRepository coffeeRepository;
	 @Autowired
	 OrderDetailsRepository orderDetailsRepository;
	 
	 private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
	    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	    @Scheduled(cron = "0 0 * * * *")
	     //  @Scheduled(initialDelay = 1000, fixedRate = 10000)
	    public List<Product> scheduleTaskForReport() {
	    	logger.info("Cron Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
	    	List<Coffee> coffee = coffeeRepository.findAll();
	    	logger.info("****Sales Report****");
	    	List<Product> sales=new ArrayList<Product>();
	    	for (Coffee cf: coffee){
	    	    Product p=new Product();
	    	    p.setProductname(cf.getCoffeeName());
	    	    p.setServes(cf.getServes());
	    		p.setQuantity(orderDetailsRepository.countByCoffe(cf.getProductId()));
	    		logger.info("Coffee Variety"+p.getProductname()+ " "+"Total Servings per day"+p.getServes()+" "+"Sold for the day"+p.getQuantity());
	    		sales.add(p);
	    	}
	    	return sales;
	    }

}
