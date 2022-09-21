package com.cg.gasbooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.gasbooking.entity.Cylinder;
@Repository
public interface CylinderRepository extends JpaRepository<Cylinder,Integer> 
{
	@Query("SELECT cust FROM Cylinder cust WHERE cust.type=:type")
	public List<Cylinder> viewCylindersByType(@Param("type") String type);
	
}
