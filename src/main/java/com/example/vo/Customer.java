package com.example.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="CUSTOMER",uniqueConstraints = { @UniqueConstraint( columnNames = { "CNAME", "MBLNUMBER" } ) })
public class Customer implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long customerid;
	
	@Column(name="CNAME")
	@NotNull
	@Size(min=2,message="Name cannot be lesser than two characters")
	@Pattern(regexp = "^[A-Za-z]+$",message="Name can have only characters")
	private String cname;
	
	
   @NotNull
    @Pattern(regexp="^[0-9]+$", message="Mobile number is invalid.")
    @Size(min=10,max=10,message="Mobile number should be ten digits")
	@Column(name="MBLNUMBER")
	private String mblnumber;
	
   @OneToMany(fetch = FetchType.LAZY,
           cascade =  CascadeType.ALL,
           mappedBy = "customer")
	    private List<Order> orders = new ArrayList<>();
	
	public Customer() {
		
	}
	
	
	
	public Customer(Long customerid, String cname, String mobileNumber) {
		super();
		this.customerid = customerid;
		this.cname = cname;
		this.mblnumber = mblnumber;
	}



	public Long getCustomerid() {
		return customerid;
	}


	public void setCustomerid(Long customerid) {
		this.customerid = customerid;
	}



	public String getCname() {
		return cname;
	}



	public void setCname(String cname) {
		this.cname = cname;
	}



	public String getMblnumber() {
		return mblnumber;
	}



	public void setMblnumber(String mblnumber) {
		this.mblnumber = mblnumber;
	}



	@Override
	public String toString() {
		return "Customer [customerid=" + customerid + ", cname=" + cname + ", mblnumber=" + mblnumber + ", orders="
				+ orders + "]";
	}


	
	
	
	
}
