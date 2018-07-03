package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.vo.Coffee;

@Repository
public interface CoffeeRepository extends JpaRepository<Coffee,Long>{

	List<Coffee> findAll();
	Coffee findByCoffeeName(String coffeeName);

	
}


