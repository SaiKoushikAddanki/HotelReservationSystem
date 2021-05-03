package com.koushik.hotel.model;

import com.koushik.hotel.entity.RoomTypes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoomDetailsDto {
	private RoomTypes roomtypes;
	private int numberOfRooms;
	private int price;
}
