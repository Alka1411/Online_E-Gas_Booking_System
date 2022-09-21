package com.cg.gasbooking.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.gasbooking.entity.Bank;
import com.cg.gasbooking.entity.Customer;
import com.cg.gasbooking.entity.Cylinder;
import com.cg.gasbooking.exception.CustomerNotFoundException;
import com.cg.gasbooking.exception.DuplicateIdException;
import com.cg.gasbooking.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository custRepo;

	@Transactional
	@Override
	public Customer insertCustomer(Customer customer) throws DuplicateIdException
	{
		Optional<Customer> custObj = custRepo.findById(customer.getCustomerId());
		if (custObj.isPresent()) 
		{
			throw new DuplicateIdException("Customer with this ID exists");
		}
		return custRepo.saveAndFlush(customer);
	}

	@Transactional
	@Override
	public Customer updateCustomer(Customer customer) throws CustomerNotFoundException
	{
		Optional<Customer> updatedCust = custRepo.findById(customer.getCustomerId());
		if (!(updatedCust.isPresent()))
		{
			throw new CustomerNotFoundException("Customer with this ID does not exists");
		}
		
		Customer updated = updatedCust.get();
		
		Cylinder cylinderObj = new Cylinder();
		cylinderObj.setCylinderId(customer.getCylinder().getCylinderId());
		cylinderObj.setPrice(customer.getCylinder().getPrice());
		cylinderObj.setStrapColor(customer.getCylinder().getStrapColor());
		cylinderObj.setType(customer.getCylinder().getType());
		cylinderObj.setWeight(customer.getCylinder().getWeight());
		updated.setCylinder(cylinderObj);
		
		Bank bankObj = new Bank();
		bankObj.setBankId(customer.getBank().getBankId());
		bankObj.setBankName(customer.getBank().getBankName());
		bankObj.setAddress(customer.getBank().getAddress());
		updated.setBank(bankObj);
		
		updated.setAddress(customer.getAddress());
		updated.setAccountNo(customer.getAccountNo());
		updated.setEmail(customer.getEmail());
		updated.setIfscNo(customer.getIfscNo());
		updated.setUsername(customer.getUsername());
		updated.setPassword(customer.getPassword());
		updated.setMobileNumber(customer.getMobileNumber());
		updated.setPan(customer.getPan());
		
		custRepo.save(updated);
		
		return updated;
	}

	@Transactional
	@Override
	public Customer deleteCustomer(int customerId) throws CustomerNotFoundException 
	{
		Optional<Customer> custObj = custRepo.findById(customerId);
		if (!(custObj.isPresent()))
		{
			throw new CustomerNotFoundException("Customer with this ID does not exists");
		}

		Customer cust = custObj.get();
		cust.setBank(null);
		cust.setCylinder(null);
		custRepo.deleteById(customerId);
		return custObj.get();
	}

	@Transactional
	@Override
	public List<Customer> viewCustomers() 
	{
		return custRepo.findAll();
	}

	@Transactional
	@Override
	public Customer viewCustomer(int customerId) 
	{
		Optional<Customer> custObj = custRepo.findById(customerId);
		Customer cust = custObj.get();
		return cust;
	}

	@Transactional
	@Override
	public Customer validateCustomer(Customer customer) throws CustomerNotFoundException 
	{
		Customer cust = custRepo.validateCustomer(customer.getUsername(),customer.getPassword());
		if (cust == null) 
		{
			throw new CustomerNotFoundException("Customer with this username and password does not exists");
		}
		return custRepo.validateCustomer(customer.getUsername(),customer.getPassword());
	}

}