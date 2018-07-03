package com.example.dto;

import java.util.Date;
import java.util.List;

import com.example.vo.Customer;

public class Receipt {

	private Long orderId;
	private Date dt;
	private List<Product> prodlist;
	private Customer cust;
	
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	public Date getDt() {
		return dt;
	}
	public void setDt(Date dt) {
		this.dt = dt;
	}
	public List<Product> getProdlist() {
		return prodlist;
	}
	public void setProdlist(List<Product> prodlist) {
		this.prodlist = prodlist;
	}
	public Customer getCust() {
		return cust;
	}
	public void setCust(Customer cust) {
		this.cust = cust;
	}
	
	
}
