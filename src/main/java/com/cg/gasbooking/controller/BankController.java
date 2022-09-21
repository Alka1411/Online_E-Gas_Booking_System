package com.cg.gasbooking.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cg.gasbooking.entity.Bank;
import com.cg.gasbooking.exception.DuplicateIdException;
import com.cg.gasbooking.service.IBankService;

/**
 * @author Adrika Awasthi
 * @all implementations related to bank which user can access
 *
 */
@RestController
@RequestMapping(path = "/Bank")
public class BankController
{

	@Autowired
	IBankService bankService;

	/**
	 * @readall admin data
	 * @return List<Bank>
	 */
	
	//http://localhost:8081/gasBooking/Bank/show
	@GetMapping(value = "/show", produces = "application/json")
	List<Bank> showBank() 
	{
		List<Bank> bankList = bankService.showBank();
		return bankList;
	}

	/**
	 * @insertdata Inserts data into bank table
	 * @param bank
	 * @return object name bank
	 * @throws DuplicateIdException
	 */

	//http://localhost:8081/gasBooking/Bank
	@PostMapping(consumes = "application/json", produces = "application/json")
	ResponseEntity<Bank> insertBank(@Valid @RequestBody Bank bank) throws DuplicateIdException
	{
		Bank bankId = bankService.insertBank(bank);
		if (bankId.getBankId() == 0)
		{
			throw new DuplicateIdException("Bank with this ID already Exsists");
		}
		return ResponseEntity.ok(bank);
	}

	/**
	 * @update update data into bank table
	 * @param bankId
	 * @return build or updated table
	 */

	//http://localhost:8081/gasBooking/Bank
	@PutMapping(consumes = "application/json", produces = "application/json")
	ResponseEntity<Bank> updatebank(@Valid @RequestBody Bank bank)
	{
		return ResponseEntity.status(200).build();
	}

	/**
	 * @delete delete the entries in bank table
	 * @param bankId
	 * @return table
	 */

	//http://localhost:8081/gasBooking/Bank/125
	@DeleteMapping("/{bankId}")
	ResponseEntity<Bank> deleteBank(@PathVariable("bankId") int bankId) 
	{
		bankService.deleteBank(bankId);
		return new ResponseEntity<Bank>(HttpStatus.OK);
	}
}