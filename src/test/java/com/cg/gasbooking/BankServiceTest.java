package com.cg.gasbooking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.gasbooking.entity.Bank;
import com.cg.gasbooking.exception.DuplicateIdException;
import com.cg.gasbooking.service.IBankService;

@SpringBootTest
class BankServiceTest
{

	@Autowired
    IBankService bankService;

	//Testing of Create bank Method
	@Test
	 public void createBankSucessfully() throws DuplicateIdException {
        Bank bank= new Bank();
        bank.setBankId(71);
        bank.setAddress("Kanpur");
        bank.setBankName("PNB");
        Bank b2 = bankService.insertBank(bank);
        assertNotNull(b2);
    }
	
	//Testing of UpdateBank Method
	@Test
	public void updateBankSucessfully() throws DuplicateIdException {
		Bank bank= new Bank();
		bank.setBankId(71);
		bank.setAddress("Unnao");
        bank.setBankName("SBI");
        Bank result = bankService.updateBank(bank);
       
		assertEquals(bank.getBankId(),result.getBankId());
		assertEquals(bank.getAddress(),result.getAddress());
		assertEquals(bank.getBankName(),result.getBankName());
		
	}
}
