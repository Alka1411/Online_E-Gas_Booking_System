package com.cg.gasbooking;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.cg.gasbooking.entity.Cylinder;
import com.cg.gasbooking.exception.CylinderNotFoundException;
import com.cg.gasbooking.exception.DuplicateIdException;
import com.cg.gasbooking.service.CylinderServiceImpl;

@SpringBootTest
class CylinderServiceTest 
{

	@Autowired
	CylinderServiceImpl cylinderService;

	// Testing of Insert Method of CylinderServiceImpl
	@Test
	public void insertCylinderTest() throws DuplicateIdException 
	{
		Cylinder cylinder = new Cylinder();
		cylinder.setCylinderId(150);
		cylinder.setPrice(850);
		cylinder.setStrapColor("blue");
		cylinder.setType("Indian");
		cylinder.setWeight(100);

		Cylinder cylinder2 = cylinderService.insertCylinder(cylinder);
		assertNotNull(cylinder2);
	}

	// Testing of Delete Method of CylinderServiceImpl
	@Test
	public void deleteAdminTest() throws CylinderNotFoundException
	{
		assertNotNull(cylinderService.deleteCylinder(142));
	}

	// Testing of DuplicateIDException of CylinderServiceImpl
	@Test
	void testDuplicateIDException()
	{
		assertThrows(DuplicateIdException.class, () -> {
			Cylinder cylinder = new Cylinder();
			cylinder.setCylinderId(144);
			cylinder.setPrice(900);
			cylinder.setStrapColor("blue");
			cylinder.setType("Indian");	
			cylinder.setWeight(25);

			cylinderService.insertCylinder(cylinder);
		});

	}

}