package com.cg.gasbooking.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.gasbooking.entity.Admin;
import com.cg.gasbooking.entity.GasBooking;
import com.cg.gasbooking.exception.AdminNotFoundException;
import com.cg.gasbooking.exception.DuplicateIdException;

@Service
public interface AdminService 
{
	List<Admin> showAdmin();
	public Admin insertAdmin(Admin admin)throws DuplicateIdException ;
	public Admin updateAdmin(Admin admin)throws AdminNotFoundException;
	public Admin deleteAdmin(int adminId) throws AdminNotFoundException;
	public List<GasBooking> getAllBookings(int customerId);
	public List<GasBooking> getAllBookingsForDays(int customerId, LocalDate fromDate, LocalDate toDate);	
}