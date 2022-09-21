package com.cg.gasbooking;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.cg.gasbooking.entity.Customer;
import com.cg.gasbooking.entity.Cylinder;
import com.cg.gasbooking.entity.SurrenderCylinder;
import com.cg.gasbooking.exception.SurrenderCylinderNotFoundException;
import com.cg.gasbooking.service.SurrenderCylinderServiceImpl;

@SpringBootTest
class SurrenderCylinderServiceTest 
{

	@Autowired
	SurrenderCylinderServiceImpl surrenderService;
	
	//Testing of SurrenderCylinderNotFoundException 
	@Test
	void TestSurrenderCylinderNotFoundException()
	{
		assertThrows(SurrenderCylinderNotFoundException.class, () -> {
			surrenderService.deleteSurrenderCylinder(1);
		});
	}

	//Testing of InserSurrenderCylinder Method
	@Test
	public void insertSurrenderCylinderTest() throws SurrenderCylinderNotFoundException 
	{
		SurrenderCylinder scylinder = new SurrenderCylinder();
		scylinder.setSurrenderDate(LocalDate.now());
		scylinder.setSurrenderId(81);
		Customer customer = new Customer();

		customer.setAccountNo("12456");
		customer.setAddress("Delhi");
		customer.setCustomerId(105);
		customer.setEmail("alkaa@gmail.com");
		customer.setIfscNo("6789");
		customer.setMobileNumber("6789455");
		customer.setPan("6789");
		customer.setPassword("7789");
		customer.setUsername("Ankita");

		Cylinder cylinder = new Cylinder();
		cylinder.setCylinderId(105);
		cylinder.setPrice(65);
		cylinder.setStrapColor("Black");
		cylinder.setType("Indian");
		cylinder.setWeight(75);
	
		SurrenderCylinder cylinder2 = surrenderService.insertSurrenderCylinder(scylinder);
		assertNotNull(cylinder2);
	}
	
	//Testing of DeleteSurrenderCylinder Method 
	@Test
	public void deleteSurrenderCylinderTest() throws SurrenderCylinderNotFoundException
	{
		assertNotNull(surrenderService.deleteSurrenderCylinder(14));
	}
}