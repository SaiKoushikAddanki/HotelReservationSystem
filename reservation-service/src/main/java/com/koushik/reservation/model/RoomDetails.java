package com.koushik.reservation.model;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.googlecode.jmapper.annotations.JMap;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Embeddable
public class RoomDetails {

	private int id;
	@Enumerated(EnumType.STRING)
	@JMap
	private RoomTypes roomtypes;
	private int numberOfRooms;
	private int price;

}
