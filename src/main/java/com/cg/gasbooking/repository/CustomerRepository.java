package com.cg.gasbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.gasbooking.entity.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer>
{
	@Query("SELECT customer from Customer customer WHERE customer.username=:username and customer.password=:password")
	public Customer validateCustomer(@Param("username") String username,@Param("password") String password);
}