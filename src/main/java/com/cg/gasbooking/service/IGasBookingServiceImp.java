package com.cg.gasbooking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.gasbooking.entity.Customer;
import com.cg.gasbooking.entity.GasBooking;
import com.cg.gasbooking.exception.DuplicateIdException;
import com.cg.gasbooking.exception.GasBookingNotFoundException;
import com.cg.gasbooking.repository.IGasBookingRepository;

@Service
public class IGasBookingServiceImp implements IGasBookingService
{
	@Autowired
	IGasBookingRepository gasRepo;
 
	@Transactional
	@Override
	public GasBooking insertGasBooking(GasBooking gasBooking) throws DuplicateIdException
	{
		Optional<GasBooking > gasboooking=gasRepo.findById(gasBooking.getGasBookingId());
		if (gasboooking.isPresent())
		{
			throw new DuplicateIdException("GasBooking  with this ID exists");
		}
		return gasRepo.saveAndFlush(gasBooking);	
	}

	@Transactional
	@Override
	public GasBooking updateGasBooking(GasBooking gasBooking) throws GasBookingNotFoundException
	{
		Optional<GasBooking> gasboooking=gasRepo.findById(gasBooking.getGasBookingId());
		if (!(gasboooking.isPresent()))
		{
			throw new GasBookingNotFoundException("Gas Booking with this ID does not exists");
		}
		
	    GasBooking updated=gasboooking.get();
	    
	    Customer cust=new Customer();
	    cust.setCustomerId(gasBooking.getCustomer().getCustomerId());
	    cust.setAccountNo(gasBooking.getCustomer().getAccountNo());
	    cust.setAddress(gasBooking.getCustomer().getAddress());
	    cust.setBank(gasBooking.getCustomer().getBank());
	    cust.setCylinder(gasBooking.getCustomer().getCylinder());
	    cust.setEmail(gasBooking.getCustomer().getEmail());
	    cust.setIfscNo(gasBooking.getCustomer().getIfscNo());
	    cust.setMobileNumber(gasBooking.getCustomer().getMobileNumber());
	    cust.setPan(gasBooking.getCustomer().getPan());
	    cust.setPassword(gasBooking.getCustomer().getPassword());
	    cust.setUsername(gasBooking.getCustomer().getUsername());
	    
	    updated.setCustomer(cust);
	    updated.setBill(gasBooking.getBill());
	    updated.setBookingDate(gasBooking.getBookingDate());
	    updated.setStatus(gasBooking.isStatus());
	    
	    return updated;
	}

	@Transactional
	@Override
	public GasBooking deleteGasBooking(int gasBookingId) throws GasBookingNotFoundException 
	{
		Optional<GasBooking> gasboooking=gasRepo.findById(gasBookingId);
		if (!(gasboooking.isPresent()))
		{
			throw new GasBookingNotFoundException("Gas Booking with this ID does not exists");
		}
		Optional<GasBooking> gasboookingdel=gasRepo.findById(gasBookingId);
		GasBooking deleteGas=gasboookingdel.get();
		deleteGas.setCustomer(null);
		gasRepo.deleteById(gasBookingId);
		return gasboookingdel.get();	
	}

	@Transactional
	@Override
	public GasBooking getBill(int customerId) throws GasBookingNotFoundException 
	{
		int gasboooking=0;
		gasboooking=gasRepo.findByCustomer(customerId);
		if (gasboooking==0) 
		{ 
			throw new GasBookingNotFoundException("Gas Booking with this customer ID does not exists");
		}
		GasBooking gasObj=gasRepo.getBill(customerId);
		return gasObj;

	}

	@Transactional
	@Override
	public List<GasBooking> showGasBooking() 
	{
        return gasRepo.findAll();
	}
}