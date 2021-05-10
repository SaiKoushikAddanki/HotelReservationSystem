package com.koushik.reservation.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReservationDetailsDto {
	private int userId;
	private String name;
	private int hotelId;
	private RoomTypes roomType;
	private Date bookingDate;
	private int numberofDays;
}
