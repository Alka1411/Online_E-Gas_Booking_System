package com.cg.gasbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.gasbooking.entity.SurrenderCylinder;

@Repository
public interface SurrenderCylinderRepository  extends JpaRepository<SurrenderCylinder,Integer> 
{
	@Query ("SELECT count(*) from SurrenderCylinder")
	public int countSurrenderedCylinders();
}
