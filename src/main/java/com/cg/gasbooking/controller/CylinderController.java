package com.cg.gasbooking.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cg.gasbooking.entity.Cylinder;
import com.cg.gasbooking.exception.CylinderNotFoundException;
import com.cg.gasbooking.exception.DuplicateIdException;
import com.cg.gasbooking.service.CylinderService;

/**
 * @author Alka
 *@implSpec All implementation related to Cylinder 
*/

@RestController
@RequestMapping(value = "/Cylinder")
public class CylinderController {

	@Autowired
	CylinderService cylinderService;
	
	/**
	 * @implSpec Reads all Cylinder data
	 * @return List<Cylinder>
	*/

	//Url = http://localhost:8081/gasBooking/cylinder/show
	@GetMapping(value = "/show", produces = "application/json")
	List<Cylinder> showCylinder() 
	{
		List<Cylinder> cylinderList = cylinderService.showCylinder();
		return cylinderList;
	}

	/**
	 * @implSpec Inserts the data into Cylinder table
	 * @return Cylinder
	 * @param Cylinder Object named cylinder
	*/
	
	//Url = http://localhost:8081/gasBooking/cylinder
	@PostMapping(consumes = "application/json", produces = "application/json")
	ResponseEntity<Cylinder> insertCylinder(@Valid @RequestBody Cylinder cylinder) throws DuplicateIdException
	{
		Cylinder cylinderObj = cylinderService.insertCylinder(cylinder);
		if (cylinderObj.getCylinderId() == 0) {
			throw new DuplicateIdException("Cylinder with this ID already Exsists");
		}
		return ResponseEntity.ok(cylinder);
	}

	/**
	 * @implSpec Updates the data 
	 * @return Cylinder
	 * @param Cylinder Object named cylinder
	 */
	
	//Url = http://localhost:8081/gasBooking/cylinder
	@PutMapping(consumes = "application/json", produces = "application/json")
	ResponseEntity<Cylinder> updateCylinder(@Valid @RequestBody Cylinder cylinder) throws CylinderNotFoundException 
	{
		Cylinder cylinderObj = cylinderService.updateCylinder(cylinder);
		if (cylinderObj.getCylinderId() == 0) 
		{
			throw new CylinderNotFoundException("Cylinder with this ID does not exists");
		}
		return new ResponseEntity<Cylinder>(cylinderObj, HttpStatus.ACCEPTED);
	}

	/**
	 * @implSpec Deletes the data 
	 * @return Cylinder
	 * @param CylinderId named as cylinderId
	*/
	
	//Url = http://localhost:8081/gasBooking/cylinder/149
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@DeleteMapping(path = "/{cylinderId}", produces = "application/json")
	ResponseEntity<Cylinder> deleteCylinder(@PathVariable("cylinderId") int cylinderId)throws CylinderNotFoundException 
	{
		Cylinder cylinderObj = cylinderService.deleteCylinder(cylinderId);
		if (cylinderObj.getCylinderId() == 0) 
		{
			throw new CylinderNotFoundException("Cylinder with this ID does not exists");
		}
		return new ResponseEntity("Cylinder with ID " + cylinderId + "is deleted", HttpStatus.OK);

	}

	/**
	 * @implSpec Reads all Cylinder data according to type
	 * @return List<Cylinder>
	 * @param type
	 
	*/
	
	// Url = http://localhost:8081/gasBooking/cylinder/Indian
	@GetMapping("/{type}")
	public ResponseEntity<List<Cylinder>> viewCylindersByType(@PathVariable("type") String type)
	{
		List<Cylinder> cylinderObj = cylinderService.viewCylindersByType(type); // custom method
		return new ResponseEntity<List<Cylinder>>(cylinderObj, HttpStatus.OK);
	}
}