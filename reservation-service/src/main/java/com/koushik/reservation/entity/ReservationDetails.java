package com.koushik.reservation.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.googlecode.jmapper.annotations.JMap;
import com.koushik.reservation.model.RoomTypes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class ReservationDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@JMap
	private int hotelId;
	@JMap
	private String name;
	private String hotelName;
	@JMap
	@Enumerated(EnumType.STRING)
	private RoomTypes roomType;
	@JMap
	private Date bookingDate;
	@JMap
	private int numberofDays;
	private int cost;
}
