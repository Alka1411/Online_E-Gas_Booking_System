package com.cg.gasbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.gasbooking.entity.Bank;

@Repository
public interface IBankRepository extends JpaRepository<Bank,Integer>
{
}