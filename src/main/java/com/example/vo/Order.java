package com.example.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="ORDERTAB")
public class Order implements Serializable{
	

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long orderid;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customerid")
	private Customer customer;
	
	@OneToMany(
			   fetch = FetchType.EAGER,
			   cascade =  CascadeType.ALL,
	           mappedBy = "order"
	    )
	private List<OrderDetails> orderDetails = new ArrayList<OrderDetails>();
	

	@Temporal(TemporalType.DATE)
	@Column(name="orderdate")
	Date orderdate;
	
    public Order(){
		
	}
	
	public Order( Customer customer, List<OrderDetails> orderDetails, Date orderdate) {
		super();
		//this.orderid = orderid;
		this.customer = customer;
		this.orderDetails = orderDetails;
		
		this.orderdate = orderdate;
	}

	public Long getOrderid() {
		return orderid;
	}

	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}

	

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<OrderDetails> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}

	
	
	public Date getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}

	@Override
	public String toString() {
		return "Order [orderid=" + orderid + ", customer=" + customer + ", orderDetails=" + orderDetails
				+ ", orderdate=" + orderdate + "]";
	}
	

}
