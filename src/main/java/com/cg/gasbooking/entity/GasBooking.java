package com.cg.gasbooking.entity;

import java.time.LocalDate;

import javax.persistence.Column; 
import javax.persistence.Entity; 
import javax.persistence.GeneratedValue; 
import javax.persistence.GenerationType; 
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity 
@Table(name="Gasbooking_Gas") 
public class GasBooking 
{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "booking_id")
	private int gasBookingId;
	
	@ManyToOne
	@JoinColumn(name= "customerId")
	private Customer customer; 
	
    @NotBlank(message="Date is mandatory")
	@Column(name="booking_date")
	private LocalDate bookingDate;
	
    @NotBlank(message="Status is mandatory")
	@AssertTrue(message ="Status of booking")
	@Column(name="status")
	private boolean status;
	
    @NotBlank(message="Bill is mandatory")
	@Min(value = 800, message = "Price should not be less than 400")
	@Max(value = 1500, message = "Price should not be greater than 800")
	@Column(name="bill")
	private float bill;
	
	public int getGasBookingId() {
		return gasBookingId;
	}
	public void setGasBookingId(int gasBookingId) {
		this.gasBookingId = gasBookingId;
	}
	
	public LocalDate getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public float getBill() {
		return bill;
	}
	public void setBill(float bill) {
		this.bill = bill;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}