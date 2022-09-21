package com.cg.gasbooking.service;

import java.util.List;

import com.cg.gasbooking.entity.Cylinder;
import com.cg.gasbooking.exception.CylinderNotFoundException;
import com.cg.gasbooking.exception.DuplicateIdException;

public interface CylinderService 
{
	List<Cylinder> showCylinder();
	Cylinder insertCylinder(Cylinder c) throws DuplicateIdException;
	Cylinder updateCylinder(Cylinder c) throws CylinderNotFoundException;
	public Cylinder deleteCylinder(int cylinderId) throws CylinderNotFoundException;
	List<Cylinder> viewCylindersByType(String type);
}
