package com.cg.gasbooking.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Admin_Gas")
public class Admin extends AbstractUser 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="admin_id")
	private int adminId;

	public Admin() 
	{
		super();
	}
	
	public Admin( int adminId,String username, String password, String address, String mobileNumber, String email) {
		super(username, password, address, mobileNumber, email);
		this.adminId = adminId;
	}

	public int getAdminId()
	{
		return adminId;
	}

	public void setAdminId(int adminId) 
	{
		this.adminId = adminId;
	}
	
	
}