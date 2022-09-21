package com.cg.gasbooking.service;

import java.util.List;

import com.cg.gasbooking.entity.GasBooking;
import com.cg.gasbooking.exception.DuplicateIdException;
import com.cg.gasbooking.exception.GasBookingNotFoundException;

public interface IGasBookingService 
{
	public GasBooking insertGasBooking(GasBooking gasBooking)throws DuplicateIdException;
	public GasBooking updateGasBooking(GasBooking gasBooking) throws GasBookingNotFoundException;
	public GasBooking deleteGasBooking(int gasBookingId) throws GasBookingNotFoundException;
	public GasBooking getBill(int customerId) throws GasBookingNotFoundException;
	public List<GasBooking> showGasBooking();
	
}
