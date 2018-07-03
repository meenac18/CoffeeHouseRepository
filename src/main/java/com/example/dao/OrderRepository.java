package com.example.dao;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.vo.Order;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<Order,Long>{

	Order findByOrderid(Long orderid);

	@Query("SELECT orderid from Order where orderdate = to_date(:#{#orderdate})")
	List<Long> findCustVal(@Param("orderdate") String orderdate);

	
	
}


