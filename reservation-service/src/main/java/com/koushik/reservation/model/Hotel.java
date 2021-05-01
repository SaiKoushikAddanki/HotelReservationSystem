package com.koushik.reservation.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.googlecode.jmapper.annotations.JMap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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
