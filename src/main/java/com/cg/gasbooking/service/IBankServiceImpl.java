package com.cg.gasbooking.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.gasbooking.entity.Bank;
import com.cg.gasbooking.exception.DuplicateIdException;
import com.cg.gasbooking.repository.IBankRepository;

@Service
public class IBankServiceImpl implements IBankService
{
	@Autowired
	IBankRepository bankRepo;

	@Transactional
	@Override
	public List<Bank> showBank() 
	{
		return bankRepo.findAll();
	}
	
	@Transactional
	@Override
	public Bank insertBank(Bank bank) throws DuplicateIdException
	{
		Optional<Bank> BankId = bankRepo.findById(bank.getBankId());
		if (BankId.isPresent())
		{
			throw new DuplicateIdException("Admin with this ID exists");
		}
		bankRepo.saveAndFlush(bank);
		return bank;
	}

	@Transactional
	@Override
	public Bank updateBank(Bank bank)
	{
		Optional<Bank> previousBank = bankRepo.findById(bank.getBankId());
		Bank updated = previousBank.get();
		updated.setAddress(bank.getAddress());
		updated.setBankName(bank.getBankName());
		updated.setBankId(bank.getBankId());
		return updated;
	}

	@Transactional
	@Override
	public Bank deleteBank(int BankId) 
	{		
		Optional<Bank> bankObj=bankRepo.findById(BankId);
		bankRepo.deleteById(BankId);
		return bankObj.get();
	}

}
