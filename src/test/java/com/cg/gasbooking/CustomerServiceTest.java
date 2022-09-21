package com.cg.gasbooking;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.gasbooking.exception.CustomerNotFoundException;
import com.cg.gasbooking.service.CustomerService;
import com.cg.gasbooking.service.IBankService;

@SpringBootTest
class CustomerServiceTest
{

	@Autowired
    IBankService BankService;

	@Autowired
	 CustomerService service;
	
	//Testing of DeleteCustomer Method
	@Test
	public void deleteCustomerTest()throws CustomerNotFoundException
	{
		assertNotNull(service.deleteCustomer(36));
	}
	
	//Testing of CustomerNotFound Exception
	@Test
	void TestCustomerNotFoundException() 
	{
		assertThrows(CustomerNotFoundException.class, ()-> {
			service.deleteCustomer(90);
		});
	}
	
}
