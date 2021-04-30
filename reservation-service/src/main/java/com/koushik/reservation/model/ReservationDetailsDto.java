package com.koushik.reservation.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDetailsDto {
	private String name;
	private String hotelName;
	private String roomType;
	private Date bookingDate;
	private int numberofDays;
}
