package com.koushik.hotel.model;

import java.util.List;

import com.googlecode.jmapper.annotations.JMap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HotelDto {
	private String name;
	private String location;
	private String category;
	private long contactNumber;
	@JMap
	private List<RoomDetailsDto> roomDetails;
}
