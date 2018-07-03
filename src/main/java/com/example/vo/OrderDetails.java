package com.example.vo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ORDERDETAILS")
public class OrderDetails implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long orderDetailId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "productId" 
    )
	private Coffee coffee;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "orderid") 
   //, foreignKey = @ForeignKey(name = "ORDER_DETAIL_ORD_FK")
  //  )
	private Order order;
	
	int quantity;
     
	public OrderDetails(){
		
	}
	
	public OrderDetails(Long orderDetailId, Coffee coffee, Order order, int quantity) {
		super();
		this.orderDetailId = orderDetailId;
		//this.coffee = coffee;
		this.order = order;
		this.quantity = quantity;
	}

	public Long getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(Long orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	/*public Coffee getCoffee() {
		return coffee;
	}

	public void setCoffee(Coffee coffee) {
		this.coffee = coffee;
	}*/

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
	@Override
	public String toString() {
		return "OrderDetails [orderDetailId=" + orderDetailId + ", coffee=" + coffee + ", order=" + order
				+ ", quantity=" + quantity + "]";
	}

	public Coffee getCoffee() {
		return coffee;
	}

	public void setCoffee(Coffee coffee) {
		this.coffee = coffee;
	}
	
	
	
}
