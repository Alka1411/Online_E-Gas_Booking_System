package com.cg.gasbooking.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "SurrenderCylinder_Gas")
public class SurrenderCylinder {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "surrender_Id")
	private int surrenderId;

	@Column(name = "surrenderDate")
	@NotNull(message = "Date is mandatory")
	private LocalDate surrenderDate;

	@Autowired
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customerId")
	Customer customer;

	@Autowired
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cylinderId")
	Cylinder cylinder;

	public int getSurrenderId() {
		return surrenderId;
	}

	public void setSurrenderId(int surrenderId) {
		this.surrenderId = surrenderId;
	}

	public LocalDate getSurrenderDate() {
		return surrenderDate;
	}

	public void setSurrenderDate(LocalDate surrenderDate) {
		this.surrenderDate = surrenderDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Cylinder getCylinder() {
		return cylinder;
	}

	public void setCylinder(Cylinder cylinder) {
		this.cylinder = cylinder;
	}

}