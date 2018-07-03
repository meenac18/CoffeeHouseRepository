package com.example.dao;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.dto.Product;
import com.example.dto.ReportDTO;
import com.example.vo.Order;
import com.example.vo.OrderDetails;

@Repository
@Transactional
public interface OrderDetailsRepository extends JpaRepository<OrderDetails,Long>{

	
	List<OrderDetails> findByOrder(Order order);
	@Query("SELECT sum(quantity) as totalSold from OrderDetails group by coffee.productId having coffee.productId=:#{#productId}")
	int countByCoffe(@Param("productId")Long productId);
	
	@Query("SELECT sum(quantity) as totalSold from OrderDetails group by coffee.productId,orderid having coffee.productId=:#{#productId} and orderid in (:#{#orderID})")
	int countByCoffeeAndDt(@Param("productId")Long productId,@Param("orderID") String orderID);
	
}


