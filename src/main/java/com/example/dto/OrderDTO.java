package com.example.dto;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class OrderDTO {
	
	private String customername;
    private String mblnumber;
   
    @JsonFormat(pattern = "dd/MM/yyyy")
       
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date orderDate;
    
    List<Product> products;

	

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getMblnumber() {
		return mblnumber;
	}

	public void setMblnumber(String mblnumber) {
		this.mblnumber = mblnumber;
	}

	
    
    

}
