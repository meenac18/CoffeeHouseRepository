package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.vo.Coffee;
import com.example.vo.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long>{

	Customer findByCnameAndMblnumber(String cname,String mblnumber);
    
	
}


