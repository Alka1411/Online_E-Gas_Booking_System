package com.cg.gasbooking.service;

import java.util.List;

import com.cg.gasbooking.entity.Customer;
import com.cg.gasbooking.exception.CustomerNotFoundException;
import com.cg.gasbooking.exception.DuplicateIdException;

public interface CustomerService 
{
	Customer insertCustomer(Customer customer) throws DuplicateIdException;
	Customer updateCustomer(Customer customer) throws CustomerNotFoundException;
	Customer deleteCustomer(int customerId) throws CustomerNotFoundException;
	List<Customer> viewCustomers();
	Customer viewCustomer(int customerId);
	Customer validateCustomer(Customer customer) throws CustomerNotFoundException;
}
