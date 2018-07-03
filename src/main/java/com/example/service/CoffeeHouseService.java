package com.example.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.controller.CoffeeHouseController;
import com.example.dao.CoffeeRepository;
import com.example.dao.CustomerRepository;
import com.example.dao.OrderDetailsRepository;
import com.example.dao.OrderRepository;
import com.example.dto.OrderDTO;
import com.example.dto.Product;
import com.example.dto.Receipt;
import com.example.vo.Coffee;
import com.example.vo.Customer;
import com.example.vo.Order;
import com.example.vo.OrderDetails;

@Service
public class CoffeeHouseService {

	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	CoffeeRepository coffeeRepository;
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	OrderDetailsRepository orderDetailsRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(CoffeeHouseController.class);
	
	public long createCustomer(Customer cust) {
		// TODO Auto-generated method stub
		logger.info("Entered createCustomer service"+cust.getCname()+" "+cust.getMblnumber());
		customerRepository.save(cust);
		logger.info("Cust Id"+cust.getCustomerid());
		return cust.getCustomerid();
	}

	public Customer searchCustomer(String cname,String mblnumber) {
		// TODO Auto-generated method stub
		Customer cust= customerRepository.findByCnameAndMblnumber(cname,mblnumber);
	   // logger.info("Search Customer Service layer"+cust.size());
	    return cust;
	}

	public long createCoffee(Coffee coffee) {
		logger.info("Entered createCustomer service");
		coffeeRepository.save(coffee);
		logger.info("Cust Id"+coffee.getProductId());
		return coffee.getProductId();
	}
	
	public List<Coffee> listCoffee() {
		// TODO Auto-generated method stub
		List<Coffee> coffee= coffeeRepository.findAll();
	   // logger.info("Search Customer Service layer"+cust.size());
	    return coffee;
	}

	@Transactional
	public long saveOrder(OrderDTO orderdto) {
		logger.info("Entered createCustomer service*****"+orderdto.getCustomername()+orderdto.getMblnumber());
		Customer cust=customerRepository.findByCnameAndMblnumber(orderdto.getCustomername(), orderdto.getMblnumber());
		//System.out.println("Cust****"+cust.getCustomerid());
		Order newOrder=new Order();
		newOrder.setCustomer(cust);
		
		newOrder.setOrderdate(orderdto.getOrderDate());
		List<OrderDetails> orderDetails=new ArrayList<OrderDetails>();
		OrderDetails odtl;
		for (Product p : orderdto.getProducts()){
		//System.out.println("Product****"+p.getProductname());
		
		Coffee coffee=coffeeRepository.findByCoffeeName(p.getProductname());
		odtl=new OrderDetails();
		odtl.setCoffee(coffee);
		
		odtl.setQuantity(p.getQuantity());
		orderDetails.add(odtl);
		orderDetailsRepository.save(odtl);
				
		}
		newOrder.setOrderDetails(orderDetails);
		Order placedOrder=orderRepository.save(newOrder);
		Long placedOrderId=placedOrder.getOrderid();
		
		orderDetails.forEach(ordrdtl -> ordrdtl.setOrder(placedOrder));
		orderDetailsRepository.save(orderDetails);
		logger.info("Done successfully");
		return placedOrderId;
	}

	public Receipt receipt(long orderid) {
		Order ord=orderRepository.findByOrderid(orderid);
		Receipt rcpt=new Receipt();
		rcpt.setOrderId(ord.getOrderid());
		rcpt.setDt(ord.getOrderdate());
		rcpt.setCust(ord.getCustomer());
		List<OrderDetails> ordtls= ord.getOrderDetails();
		List<Product> products=new ArrayList<Product>();
		
		for (OrderDetails ordtl : ordtls){
			Product p=new Product();
			p.setProductname(ordtl.getCoffee().getCoffeeName());
			p.setQuantity(ordtl.getQuantity());
			p.setPrice(ordtl.getCoffee().getPrice());
			p.setAmt(p.getQuantity()*p.getPrice());
			products.add(p);
		}
		rcpt.setProdlist(products);
		
		return rcpt;
	}

	public List<Product> report(String dt) {
		
		
		List<Coffee> coffee = coffeeRepository.findAll();
		SimpleDateFormat sf=new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sf1=new SimpleDateFormat("dd MMM yyyy");
		//SimpleDateFormat sf1=new SimpleDateFormat("")
		Date d=null,targetDate=null;
		
		 List<Long> order=new ArrayList<Long>();
		try {
			 d=sf.parse(dt);
			 String s =sf1.format(d);
			 targetDate=sf1.parse(s);
			 logger.info("Printing date "+d+" : "+s+"targetDate"+targetDate);
			 order=orderRepository.findCustVal(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String ordList = order.stream()
			      .map(n -> String.valueOf(n))
			      .collect(Collectors.joining(","));
	    
		logger.info("Printing order list for "+d+" : "+ordList.toString()+":"+dt);
		List<Product> sales=new ArrayList<Product>();
		for (Coffee cf: coffee){
			
		    Product p=new Product();
		    p.setProductname(cf.getCoffeeName());
		    p.setServes(cf.getServes());
			p.setQuantity(orderDetailsRepository.countByCoffeeAndDt(cf.getProductId(),ordList));
			logger.info("Coffee Variety"+p.getProductname()+ " "+"Total Servings per day"+p.getServes()+" "+"Sold for the day"+p.getQuantity());
			sales.add(p);
		}
	        return sales;
		
	}

}
