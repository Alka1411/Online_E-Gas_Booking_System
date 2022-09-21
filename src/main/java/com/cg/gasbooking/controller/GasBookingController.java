package com.cg.gasbooking.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cg.gasbooking.entity.GasBooking;
import com.cg.gasbooking.exception.DuplicateIdException;
import com.cg.gasbooking.exception.GasBookingNotFoundException;
import com.cg.gasbooking.service.IGasBookingService;

/**
 * @author Ria Mishra
 * @implSpec All implementations related to gas booking
 */

@RestController
@RequestMapping(value = "/GasBooking")
public class GasBookingController
{
	@Autowired
	IGasBookingService gasService;
	
	/**
	 * @implSpec Reads all  GasBooking data
	 * @return  List<GasBooking>
	 */
	
	//http://localhost:8080/gasBooking/GasBooking/show
	@GetMapping(value = "/show", produces = "application/json")
	List<GasBooking> showGasBooking() 
	{
		List<GasBooking> gbList = gasService.showGasBooking();
		return gbList;
	}

	/**
	 * @implSpec Inserts data into GasBooking table
	 * @return GasBooking
	 * @param GasBooking object named gb
	 */
	
	//http://localhost:8080/gasBooking/GasBooking
	@PostMapping(consumes = "application/json", produces = "application/json")
	ResponseEntity<GasBooking> insertGasBooking( @Valid  @RequestBody GasBooking gb) throws DuplicateIdException
	{
		GasBooking gId = gasService.insertGasBooking(gb);
		if (gId.getGasBookingId() == 0)
		{
			throw new DuplicateIdException("Gas Booking with this ID already Exsists");
		}
		return ResponseEntity.ok(gb);
	}
	
	/**
	 * @implSpec Update the data in GasBooking table
	 * @return GasBooking
	 * @param GasBooking object named gb
	 */

	//http://localhost:8080/gasBooking/GasBooking
	@PutMapping(consumes = "application/json", produces = "application/json")
	ResponseEntity<GasBooking> updateGasBooking( @Valid  @RequestBody GasBooking gb) throws GasBookingNotFoundException 
	{
		GasBooking gId = gasService.updateGasBooking(gb);
		if (gId.getGasBookingId() == 0) 
		{
			throw new GasBookingNotFoundException("GasBooking with ID" + gb.getGasBookingId() + "does not exist");
		}
		return new ResponseEntity<GasBooking>(gId, HttpStatus.ACCEPTED);
	}

	/**
	 * @implSpec Deletes the data of given ID in GasBooking table
	 * @return GasBooking
	 * @param GasBooking ID
	 */
	
	//http://localhost:8080/gasBooking/GasBooking/125
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@DeleteMapping("/{gasBookingId}")
	ResponseEntity<GasBooking> deleteGasBooking(@PathVariable("gasBookingId") int gasBookingId)throws GasBookingNotFoundException
	{
		GasBooking gId = gasService.deleteGasBooking(gasBookingId);
		if (gId.getGasBookingId() == 0)
		{
			throw new GasBookingNotFoundException("GasBooking with ID" + gasBookingId + "does not exist");
		}
		
		return new ResponseEntity("GasBooking with ID " + gasBookingId + " is deleted", HttpStatus.OK);
	}

	/**
	 * @implSpec Reads all GasBooking data of given ID
	 * @return  GasBooking
	 * @param Customer ID
	 */
	
	//http://localhost:8080/gasBooking/GasBooking/125
	@GetMapping(value = "/{customerId}", produces = "application/json")
	ResponseEntity<GasBooking> getBill(@PathVariable("customerId") int customerId) throws GasBookingNotFoundException 
	{
		GasBooking gb = gasService.getBill(customerId);
		if (gb == null) 
		{
			throw new GasBookingNotFoundException("GasBooking with " + customerId + "is not available");
		}
		return new ResponseEntity<GasBooking>(gb, HttpStatus.ACCEPTED);
	}
}