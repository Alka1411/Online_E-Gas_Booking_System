package com.cg.gasbooking.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity 
@Table(name="Cylinder_Gas")
public class Cylinder 
{ 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cylinderId")
	private int cylinderId;

	@Column(name = "type")
	@NotBlank(message = "Type is mandatory")
	private String type;

	@Column(name = "weight")
	@NotNull(message = "Weight should not be null")
	private float weight;

	@Column(name = "strapColor")
	@NotBlank(message = "StrapColor should not be null")
	private String strapColor;

	@Column(name = "price")
	@NotNull(message="Price should not be null")
	@Min(value = 800, message = "Price starting from 800")
	@Max(value = 1500, message = "Price ended with 1000")
	private float price;
	
	@OneToOne(mappedBy = "cylinder")
	private Customer customer;
	
	public int getCylinderId() 
	{
		return cylinderId;
	}
	public void setCylinderId(int cylinderId) 
	{
		this.cylinderId = cylinderId;
	}
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public float getWeight()
	{
		return weight;
	}
	public void setWeight(float weight) 
	{
		this.weight = weight;
	}
	public String getStrapColor() 
	{
		return strapColor;
	}
	public void setStrapColor(String strapColor)
	{
		this.strapColor = strapColor;
	}
	public float getPrice() 
	{
		return price;
	}
	public void setPrice(float price)
	{
		this.price = price;
	} 
	
	
}