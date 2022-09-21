package com.cg.gasbooking.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cg.gasbooking.entity.Customer;
import com.cg.gasbooking.exception.CustomerNotFoundException;
import com.cg.gasbooking.exception.DuplicateIdException;
import com.cg.gasbooking.service.CustomerService;

/**
 * @author Kavya Jain
 * @implSpec All implementation related to Customer which customer can access
 */

@RestController
@RequestMapping(value = "/Customer")
public class CustomerController 
{
	@Autowired
	CustomerService custService;

	/**
	 * @implSpec inserts a customer into the database
	 * @return Customer
	 * @param customer
	 */

	//http://localhost:8080/gasBooking/Customer
	@PostMapping(consumes = "application/json", produces = "application/json")
	ResponseEntity<Customer> insertCustomer(@Valid @RequestBody Customer customer) throws DuplicateIdException 
	{
		Customer cust = custService.insertCustomer(customer);
		if (cust.getCustomerId() == 0)
		{
			throw new DuplicateIdException("Customer with this ID already Exsists");
		}
		return ResponseEntity.ok(customer);
	}

	/**
	 * @implSpec updates the details a customer into the database
	 * @return Customer
	 * @param customer
	 */
	
	//http://localhost:8080/gasBooking/Customer
	@PutMapping(consumes = "application/json", produces = "application/json")
	ResponseEntity<Customer> updateCustomer(@Valid @RequestBody Customer customer) throws CustomerNotFoundException
	{
		Customer custId = custService.updateCustomer(customer);
		if (custId.getCustomerId() == 0) 
		{
			throw new CustomerNotFoundException("Customer with ID " + customer.getCustomerId() + " does not exist");
		}
		return ResponseEntity.status(200).build();
	}

	/**
	 * @implSpec deletes a customer from the database
	 * @return Customer
	 * @param customer Id
	 */

	//http://localhost:8080/gasBooking/Customer/152
	@DeleteMapping(path = "{custId}", produces = "application/json")
	ResponseEntity<Customer> deleteCustomer(@PathVariable("custId") int customerId) throws CustomerNotFoundException 
	{
		Customer custid = custService.deleteCustomer(customerId);
		if (custid.getCustomerId() == 0)
		{
			throw new CustomerNotFoundException("Customer with ID " + custid.getCustomerId() + " does not exist");
		}
		return ResponseEntity.status(200).build();
	}

	/**
	 * @implSpec reads all customer data
	 * @return List<Customer>
	 */

	//http://localhost:8080/gasBooking/Customer
	@GetMapping(produces = "application/json")
	List<Customer> viewCustomers() 
	{
		List<Customer> custList = custService.viewCustomers();
		return custList;
	}

	/**
	 * @implSpec reads the customer data whose Id is given
	 * @return Customer
	 * @param customer Id
	 */

	//http://localhost:8080/gasBooking/Customer/1254
	@GetMapping(path = "{custId}", produces = "application/json")
	ResponseEntity<Customer> viewCustomer(@PathVariable("custId") int customerId) 
	{
		Customer c = custService.viewCustomer(customerId);
		return ResponseEntity.ok(c);
	}

	/**
	 * @implSpec validates a customer by checking the username and password
	 * @return Customer
	 * @param username,password
	 */

	//http://localhost:8080/gasBooking/Customer/125
	@PostMapping(path = "/custId", consumes = "application/json", produces = "application/json")
	ResponseEntity<Customer> validateCustomer(@RequestBody Customer customer) throws CustomerNotFoundException
	{
		Customer cust = custService.validateCustomer(customer);
		if (cust == null)
		{
			throw new CustomerNotFoundException("Customer with username and password not available!");
		}
		return new ResponseEntity<Customer>(cust, HttpStatus.OK);
	}
}