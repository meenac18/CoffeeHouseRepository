package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.controller.CoffeeHouseController;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=CoffeeHouseController.class)
//,,webEnvironment = WebEnvironment.NONE
@TestPropertySource(locations="classpath:test.properties")
//@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
public class DemoApplicationTests {

	@Test
	public void contextLoads() {
	}

}
