package com.cg.gasbooking.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cg.gasbooking.entity.SurrenderCylinder;
import com.cg.gasbooking.exception.SurrenderCylinderNotFoundException;
import com.cg.gasbooking.service.SurrenderCylinderService;

/**
 * @author Alka
 *@implSpec All implementation related to Surrender Cylinder
*/

@RestController
@RequestMapping(value = "/SurrenderCylinder")
public class SurrenderCylinderController 
{
	@Autowired
	SurrenderCylinderService surrenderService;

	/**
	 * @implSpec Reads all Surrender Cylinder data
	 * @return List<SurrenderCylinder> 
	*/
	
	//Url = http://localhost:8081/gasBooking/surrendercylinder/show
	@GetMapping(value = "/show", produces = "application/json")
	List<SurrenderCylinder> showSurrenderCylinder() 
	{
		List<SurrenderCylinder> surrenderList = surrenderService.showSurrenderCylinder();
		return surrenderList;
	}

	/**
	 * @implSpec Inserts the data into Cylinder table
	 * @return Cylinder
	 * @param Surrender Cylinder Object named sc
	*/
	
	//Url = http://localhost:8081/gasBooking/surrendercylinder
	@PostMapping(consumes = "application/json", produces = "application/json")
	ResponseEntity<SurrenderCylinder> insertSurrenderCylinder(@Valid @RequestBody SurrenderCylinder sc)throws SurrenderCylinderNotFoundException 
	{
		SurrenderCylinder surrenderCylinder = surrenderService.insertSurrenderCylinder(sc);
		if (surrenderCylinder.getSurrenderId() == 0) 
		{
			throw new SurrenderCylinderNotFoundException("SurrenderCylinder with this ID does not exists");
		}
		return ResponseEntity.ok(sc);
	}

	/**
	 * @implSpec Updates the data 
	 * @return SurrenderCylinder
	 * @param SurrenderCylinder Object named sc
	 * 
	*/
	//Url = http://localhost:8081/gasBooking/surrendercylinder
	@PutMapping(consumes = "application/json", produces = "application/json")
	ResponseEntity<SurrenderCylinder> updateSurrenderCylinder(@Valid @RequestBody SurrenderCylinder sc)throws SurrenderCylinderNotFoundException 
	{
		SurrenderCylinder surrenderCylinder = surrenderService.updateSurrenderCylinder(sc);
		if (surrenderCylinder.getSurrenderId() == 0) 
		{
			throw new SurrenderCylinderNotFoundException("SurrenderCylinder with this ID does not exists");
		}
		return new ResponseEntity<SurrenderCylinder>(surrenderCylinder, HttpStatus.ACCEPTED);
	}

	/**
	 * @implSpec Deletes the data 
	 * @return SurrenderCylinder
	 * @param SurrenderId named as surrenderId
	*/
	
	//Url = http://localhost:8081/gasBooking/surrendercylinder/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@DeleteMapping("/{surrenderId}")
	ResponseEntity<SurrenderCylinder> deleteSurrenderCylinder(@PathVariable("surrenderId") int surrenderId)throws SurrenderCylinderNotFoundException
	{
		SurrenderCylinder surrenderCylinder = surrenderService.deleteSurrenderCylinder(surrenderId);
		if (surrenderCylinder.getSurrenderId() == 0) {
			throw new SurrenderCylinderNotFoundException("SurrenderCylinder with this ID does not exists");
		}
		return new ResponseEntity("SurrenderCylinder with ID  " + surrenderId + " is deleted", HttpStatus.OK);
	}

	/**
	 * @implSpec Counts the records 
	 * @return SurrenderCylinder
	*/
	
	//Url = http://localhost:8081/gasBooking/surrendercylinder/count
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping(value = "/count", produces = "application/json")
	ResponseEntity<SurrenderCylinder> countSurrenderedCylinders()
	{
		int count = surrenderService.countSurrenderedCylinders();
		return new ResponseEntity(count, HttpStatus.OK);
	}
}