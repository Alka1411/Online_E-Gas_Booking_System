package com.cg.gasbooking.entity;

import javax.persistence.CascadeType; 
import javax.persistence.Column; 
import javax.persistence.Entity; 
import javax.persistence.Id; 
import javax.persistence.JoinColumn; 
import javax.persistence.OneToOne; 
import javax.persistence.Table;
import javax.validation.constraints.NotBlank; 

@Entity 
@Table(name="Bank_Gas") 
public class Bank 
{ 
	@Id 
	@Column(name ="Bank") 
	private int bankId; 
	
	@NotBlank(message = "Bank Name is mandatory")
	@Column(name ="Bank_Name") 
	private String bankName; 
	
	@NotBlank(message = "Address is mandatory")
	@Column(name ="Address") 
	private String address; 
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="bank")
	private Customer customer;
	
	public int getBankId() 
	{
		return bankId;
	} 
	public void setBankId(int bankId) 
	{ 
		this.bankId = bankId; 
	}
	public String getBankName() 
	{ 
		return bankName; 
	} 
	public void setBankName(String bankName)
	{
		this.bankName = bankName;
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

