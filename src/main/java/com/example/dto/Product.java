package com.example.dto;

public class Product {

	private String productname;
	private int quantity;
	private long price;
	private long amt;
	private int serves;
	
	
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public long getAmt() {
		return amt;
	}
	public void setAmt(long amt) {
		this.amt = amt;
	}
	public int getServes() {
		return serves;
	}
	public void setServes(int serves) {
		this.serves = serves;
	}
	@Override
	public String toString() {
		return "Product [productname=" + productname + ", quantity=" + quantity + ", price=" + price + ", amt=" + amt
				+ ", serves=" + serves + "]";
	}

	
}	
