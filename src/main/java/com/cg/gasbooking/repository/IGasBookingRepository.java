package com.cg.gasbooking.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.gasbooking.entity.GasBooking;

@Repository
public interface IGasBookingRepository extends JpaRepository<GasBooking, Integer> {

	@Query("Select booking from GasBooking booking where booking.customer.customerId=:customerId")
	public List<GasBooking> getAllBookings(@Param("customerId") int customerId);

	//@Query("Select booking from GasBooking booking where booking.customer.customerId=:customerId and booking.bookingDate BETWEEN :fromDate AND :toDate")
	@Query("Select booking from GasBooking booking where booking.customer.customerId=:customerId and booking.bookingDate<=:toDate AND booking.bookingDate>=:fromDate")
	public List<GasBooking>getAllBookingsForDays(@Param("customerId")int customerId,@Param("fromDate")LocalDate fromDate,@Param("toDate")LocalDate toDate);

	@Query("SELECT gasBooking from GasBooking gasBooking WHERE gasBooking.customer.customerId=:customerId")
	public GasBooking getBill(@Param("customerId") int customerId);

	@Query("SELECT count(*) from GasBooking WHERE customer.customerId=:customerId")
	public int findByCustomer(@Param("customerId") int customerId);
}