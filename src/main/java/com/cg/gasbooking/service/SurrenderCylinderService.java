package com.cg.gasbooking.service;

import java.util.List;

import com.cg.gasbooking.entity.SurrenderCylinder;
import com.cg.gasbooking.exception.SurrenderCylinderNotFoundException;

public interface SurrenderCylinderService
{
	List<SurrenderCylinder> showSurrenderCylinder();
	public SurrenderCylinder insertSurrenderCylinder(SurrenderCylinder surrenderCylinder)throws SurrenderCylinderNotFoundException;
	public SurrenderCylinder updateSurrenderCylinder(SurrenderCylinder surrenderCylinder)throws SurrenderCylinderNotFoundException;
	public SurrenderCylinder deleteSurrenderCylinder(int surrenderCylinderId)throws SurrenderCylinderNotFoundException;
	public int countSurrenderedCylinders();
}