package com.cg.gasbooking.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@MappedSuperclass
public abstract class AbstractUser 
{
	@Column(name="Username")
	@NotBlank(message = "Name is mandatory")
	private String username;
	
	@NotBlank(message = "Password is mandatory")
	@Size(min = 8, max=12, message = "Password should not less than 8 and greater than 12")
	@Column(name="Password")
	private String password;
	
	@NotBlank(message = "Address is mandatory")
	@Column(name="Address")
	private String address;
	
	@NotBlank(message = "Mobile Number is mandatory")
	@Size(min = 10, max = 10, message = "Enter 10 digit valid mobile number ")
	@Column(name="Mobile_Number")
	private String mobileNumber;
	
	@NotBlank(message = "Email is mandatory")
	@Email(message = "Enter a valid Email")
	@Column(name="Email")
	private String email;
	
	public AbstractUser()
	{
		
	}
	
	public AbstractUser(String username, String password, String address, String mobileNumber, String email) {
		super();
		this.username = username;
		this.password = password;
		this.address = address;
		this.mobileNumber = mobileNumber;
		this.email = email;
	}
	
	public String getUsername() 
	{
		return username;
	}
	public void setUsername(String username) 
	{
		this.username = username;
	} 
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password) 
	{ 
		this.password = password;
	}
	public String getMobileNumber() 
	{
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) 
	{
		this.mobileNumber = mobileNumber;
	}
	public String getEmail() 
	{
		return email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	public String getAddress()
	{
		return address;
	}
	public void setAddress(String address) 
	{
		this.address = address;
	}
		
}
