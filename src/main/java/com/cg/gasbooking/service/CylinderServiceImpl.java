package com.cg.gasbooking.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.gasbooking.entity.Cylinder;
import com.cg.gasbooking.exception.CylinderNotFoundException;
import com.cg.gasbooking.exception.DuplicateIdException;
import com.cg.gasbooking.repository.CylinderRepository;

@Service
public class CylinderServiceImpl implements CylinderService 
{
	@Autowired
	CylinderRepository cylinderRepo;

	@Override
	public List<Cylinder> showCylinder() 
	{	
		return cylinderRepo.findAll();
	}

	@Transactional
	@Override
	public Cylinder insertCylinder(Cylinder cylider) throws DuplicateIdException 
	{
		Optional<Cylinder> cyliderObj = cylinderRepo.findById(cylider.getCylinderId());
		if (cyliderObj.isPresent()) 
		{
			throw new DuplicateIdException("Cylinder with this ID exists");
		}
		cylinderRepo.saveAndFlush(cylider);
		return cylider;
	}

	@Transactional
	@Override
	public Cylinder updateCylinder(Cylinder cylider) throws CylinderNotFoundException 
	{
		Optional<Cylinder> previousCylinder = cylinderRepo.findById(cylider.getCylinderId());
		if (!(previousCylinder.isPresent())) 
		{
			throw new CylinderNotFoundException("Cylinder with this ID does not exists");
		}
		Cylinder updated = previousCylinder.get();
		updated.setType(cylider.getType());
		updated.setPrice(cylider.getPrice());
		updated.setStrapColor(cylider.getStrapColor());
		updated.setWeight(cylider.getWeight());
		return updated;
	}

	@Transactional
	@Override
	public Cylinder deleteCylinder(int cylinderId) throws CylinderNotFoundException
	{
		Optional<Cylinder> cyliderObj = cylinderRepo.findById(cylinderId);
		if (!(cyliderObj.isPresent()))
		{
			throw new CylinderNotFoundException("Cylinder with this ID does not exists");
		}
		cylinderRepo.deleteById(cylinderId);
		return cyliderObj.get();
	}

	@Transactional
	@Override
	public List<Cylinder> viewCylindersByType(String type)
	{
		List<Cylinder> cyliderObj = cylinderRepo.viewCylindersByType(type);// custom methods of repository
		return cyliderObj;
	}

}