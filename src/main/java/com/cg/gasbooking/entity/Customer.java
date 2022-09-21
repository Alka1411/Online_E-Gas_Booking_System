package com.cg.gasbooking.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="Customer_Gas")
public class Customer extends AbstractUser 
{
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="customer_id")
	private int customerId; 
	
	@OneToOne(cascade= CascadeType.ALL) 
	@JoinColumn(name= "cylinder_id",referencedColumnName = "cylinderId") 
	private Cylinder cylinder; 
	
	@OneToOne(cascade= CascadeType.ALL) 
	@JoinColumn(name= "bankId")
	private Bank bank;
		
	@OneToMany(mappedBy="customer",cascade=CascadeType.ALL)
	private List<GasBooking> booking = new ArrayList<>();	
	
	@NotBlank(message = "Name is mandatory")
	@Size(min = 9, max=18, message = "Enter a valid account number")
	@Column(name="accountNo")
	private String accountNo; 
	
	@NotBlank(message = "ifscNo is mandatory")
	@Size(min = 11, max=11, message = "Enter a valid IFSC number")
	@Column(name="ifscNo")
	private String ifscNo; 
	
	@NotBlank(message = "Pan is mandatory")
	@Size(min = 10, max=10, message = "Enter a valid PAN number")
	@Column(name="pan")
	private String pan;
	
	public Customer() 
	{
		super();
	}
	public Customer(String username, String password, String address, String mobileNumber, String email, int customerId,
			Cylinder cylinder, Bank bank, List<GasBooking> booking, String accountNo, String ifscNo, String pan) 
	{
		super(username, password, address, mobileNumber, email);
		this.customerId = customerId;
		this.cylinder = cylinder;
		this.bank = bank;
		this.booking = booking;
		this.accountNo = accountNo;
		this.ifscNo = ifscNo;
		this.pan = pan;
	}

	
	public int getCustomerId() 
	{
		return customerId;
	}

	public void setCustomerId(int customerId) 
	{
		this.customerId = customerId;
	}

	public Cylinder getCylinder()
	{
		return cylinder;
	}

	public void setCylinder(Cylinder cylinder)
	{
		this.cylinder = cylinder;
	}

	public Bank getBank() 
	{
		return bank;
	}

	public void setBank(Bank bank)
	{
		this.bank = bank;
	}

	public String getAccountNo()
	{
		return accountNo;
	}

	public void setAccountNo(String accountNo) 
	{
		this.accountNo = accountNo;
	}

	public String getIfscNo()
	{
		return ifscNo;
	}

	public void setIfscNo(String ifscNo)
	{
		this.ifscNo = ifscNo;
	}

	public String getPan() 
	{
		return pan;
	}

	public void setPan(String pan)
	{
		this.pan = pan;
	} 

}
