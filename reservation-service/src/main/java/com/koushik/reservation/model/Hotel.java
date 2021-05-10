package com.koushik.reservation.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Hotel {

	private int hotelId;
	private String name;
	private String location;
	private String category;
	private long contactNumber;
	@OneToMany(targetEntity = RoomDetails.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "hotelId", referencedColumnName = "hotelId")
	private List<RoomDetails> roomDetails;
	
}
