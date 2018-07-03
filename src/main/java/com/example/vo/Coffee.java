package com.example.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="coffee")
public class Coffee implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long productId;
	
	@Column(name="coffeename")
	private String coffeeName;
	@Column(name="desc")
	private String desc;
	@Column(name="serves")
	private int serves;
	@Column(name="price")
	private int price;
	
	
	
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getCoffeeName() {
		return coffeeName;
	}
	public void setCoffeeName(String coffeeName) {
		this.coffeeName = coffeeName;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getServes() {
		return serves;
	}
	public void setServes(int serves) {
		this.serves = serves;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Coffee [productId=" + productId + ", coffeeName=" + coffeeName + ", desc=" + desc + ", serves=" + serves
				+ ", price=" + price + "]";
	}
	
	
	
	
	
	
}
