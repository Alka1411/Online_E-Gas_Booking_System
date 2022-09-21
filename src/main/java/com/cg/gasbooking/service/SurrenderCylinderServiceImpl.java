package com.cg.gasbooking.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.gasbooking.entity.Customer;
import com.cg.gasbooking.entity.Cylinder;
import com.cg.gasbooking.entity.SurrenderCylinder;
import com.cg.gasbooking.exception.SurrenderCylinderNotFoundException;
import com.cg.gasbooking.repository.SurrenderCylinderRepository;

@Service
public class SurrenderCylinderServiceImpl implements SurrenderCylinderService 
{

	@Autowired
	SurrenderCylinderRepository surrenderRepo;

	@Override
	public List<SurrenderCylinder> showSurrenderCylinder() 
	{
		return surrenderRepo.findAll();
	}

	@Transactional
	@Override
	public SurrenderCylinder insertSurrenderCylinder(SurrenderCylinder surrenderCylinder)throws SurrenderCylinderNotFoundException 
	{
		Optional<SurrenderCylinder> surrenderCylinderObj = surrenderRepo.findById(surrenderCylinder.getSurrenderId());
		
		if (surrenderCylinderObj.isPresent()) {
			throw new SurrenderCylinderNotFoundException("SurrenderCylinder with this ID does not exists");
		}
		return surrenderRepo.saveAndFlush(surrenderCylinder);
	}

	@Transactional
	@Override
	public SurrenderCylinder updateSurrenderCylinder(SurrenderCylinder surrenderCylinder)throws SurrenderCylinderNotFoundException 
	{
		Optional<SurrenderCylinder> previousCylinder = surrenderRepo.findById(surrenderCylinder.getSurrenderId());
		if (!(previousCylinder.isPresent())) 
		{
			throw new SurrenderCylinderNotFoundException("SurrenderCylinder with this ID does not exists");
		}
		SurrenderCylinder updated = previousCylinder.get();
		
		updated.setSurrenderDate(surrenderCylinder.getSurrenderDate());
		Customer cust = new Customer();
		cust.setCustomerId(surrenderCylinder.getCustomer().getCustomerId());
		cust.setAccountNo(surrenderCylinder.getCustomer().getAccountNo());
		cust.setAddress(surrenderCylinder.getCustomer().getAddress());
		cust.setBank(surrenderCylinder.getCustomer().getBank());
		cust.setCylinder(surrenderCylinder.getCustomer().getCylinder());
		cust.setEmail(surrenderCylinder.getCustomer().getEmail());
		cust.setIfscNo(surrenderCylinder.getCustomer().getIfscNo());
		cust.setMobileNumber(surrenderCylinder.getCustomer().getMobileNumber());
		cust.setPan(surrenderCylinder.getCustomer().getPan());
		cust.setPassword(surrenderCylinder.getCustomer().getPassword());
		cust.setUsername(surrenderCylinder.getCustomer().getUsername());
		updated.setCustomer(cust);

		Cylinder cylinder = new Cylinder();
		cylinder.setCylinderId(surrenderCylinder.getCylinder().getCylinderId());
		cylinder.setPrice(surrenderCylinder.getCylinder().getPrice());
		cylinder.setStrapColor(surrenderCylinder.getCylinder().getStrapColor());
		cylinder.setType(surrenderCylinder.getCylinder().getType());
		cylinder.setWeight(surrenderCylinder.getCylinder().getWeight());
		updated.setCylinder(cylinder);
		surrenderRepo.save(updated);

		return updated;

	}

	@Transactional
	@Override
	public SurrenderCylinder deleteSurrenderCylinder(int surrenderId) throws SurrenderCylinderNotFoundException
	{
		Optional<SurrenderCylinder> surrenderCylinder = surrenderRepo.findById(surrenderId);
		if (!(surrenderCylinder.isPresent())) 
		{
			throw new SurrenderCylinderNotFoundException("SurrenderCylinder with this ID does not exists");
		}
		Optional<SurrenderCylinder> surrenderCylinderObj = surrenderRepo.findById(surrenderId);

		SurrenderCylinder sc = surrenderCylinderObj.get();
		sc.setCustomer(null);
		sc.setCylinder(null);
		surrenderRepo.deleteById(surrenderId);
		return surrenderCylinderObj.get();
	}

	@Transactional
	@Override
	public int countSurrenderedCylinders() 
	{
		return surrenderRepo.countSurrenderedCylinders();
	}
}