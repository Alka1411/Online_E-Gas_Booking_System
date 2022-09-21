package com.cg.gasbooking;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.cg.gasbooking.exception.GasBookingNotFoundException;
import com.cg.gasbooking.service.IGasBookingService;

@SpringBootTest
class GasBookingServiceTest
{
	@Autowired
	IGasBookingService gasService;

	// Testing of Delete GasBooking Method
	@Test
	public void deleteGasBookingTest() throws GasBookingNotFoundException 
	{
		assertNotNull(gasService.deleteGasBooking(32));
	}
	
	// Testing of GasBookingNotFound Exception
	@Test
	void TestGasBookingNotFoundException() 
	{
		assertThrows(GasBookingNotFoundException.class, () -> {
			gasService.deleteGasBooking(34);
		});
	}
}