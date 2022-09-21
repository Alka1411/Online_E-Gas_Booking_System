package com.cg.gasbooking.service;

import java.util.List;

import com.cg.gasbooking.entity.Bank;
import com.cg.gasbooking.exception.DuplicateIdException;

public interface IBankService
{
	public Bank insertBank(Bank bank)throws DuplicateIdException;
	public Bank updateBank(Bank bank);
	public Bank deleteBank(int bankId);
	public List<Bank> showBank();	
}


