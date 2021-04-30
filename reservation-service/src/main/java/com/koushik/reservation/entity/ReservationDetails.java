package com.koushik.reservation.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ReservationDetails {

	@Id
	private String name;
	private String hotelName;
	private String roomType;
	private Date bookingDate;
	private int numberofDays;
}
