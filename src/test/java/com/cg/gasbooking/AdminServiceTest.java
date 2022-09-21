package com.cg.gasbooking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.gasbooking.entity.Admin;
import com.cg.gasbooking.exception.AdminNotFoundException;
import com.cg.gasbooking.exception.DuplicateIdException;
import com.cg.gasbooking.service.AdminService;

@SpringBootTest//(OnlineEGasBookingApplication.class)
class AdminServiceTest
{
	@Autowired
	AdminService adminService;
	
	//Testing of InsertAdmin Method 
	@Test
	public void insertAdminTest() throws DuplicateIdException 
	{
		Admin admin=new Admin(148,"Anshika","anshika26","B-12 Vijay Nagar","9876812598","02ans@gmail.com");
		assertNotNull(adminService.insertAdmin(admin));			
	}

	//Testing of UpdateAdmin Method 
	@Transactional
	@Test
	public void TestupdateAdmin()throws AdminNotFoundException
	{		
		Admin admin=new Admin(148,"Rishabh","rishi","B-12 Vijay Nagar","9876812598","09Rishi@gmail.com");
		Admin result=adminService.updateAdmin(admin);
		
		assertEquals(admin.getAdminId(),result.getAdminId());
		assertEquals(admin.getUsername(),result.getUsername());
		assertEquals(admin.getPassword(),result.getPassword());
		assertEquals(admin.getMobileNumber(),result.getMobileNumber());
		assertEquals(admin.getEmail(),result.getEmail());
		assertEquals(admin.getAddress(),result.getAddress());
	}
	
	//Testing of DeleteAdmin Method 
	@Test
	public void deleteAdminTest()throws AdminNotFoundException
	{
		assertNotNull(adminService.deleteAdmin(144));
	}
	
	//Testing of AdminNotFound Exception
	@Test
	void TestAdminNotFoundException() 
	{
		assertThrows(AdminNotFoundException.class, () -> {
			adminService.deleteAdmin(101);
		});
	}
	
	//Testing of DuplicateId Exception
	@Test
	void TestDuplicateIdException()
	{
		assertThrows(DuplicateIdException.class, () -> {
			Admin admin=new Admin(149,"Rishabh","rishi","B-12 Vijay Nagar","9876812598","09Rishi@gmail.com");
			adminService.insertAdmin(admin);
		});
	}
}