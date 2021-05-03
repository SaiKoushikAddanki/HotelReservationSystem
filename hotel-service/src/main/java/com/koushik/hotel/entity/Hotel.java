package com.koushik.hotel.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.googlecode.jmapper.annotations.JMap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int hotelId;
	@JMap
	@NotEmpty(message="Please enter your name")
	private String name;
	@JMap
	@NotBlank(message="Please enter location details")
	private String location;
	@JMap
	@NotBlank(message="Please enter the hotel category")
	private String category;
	@JMap
	private long contactNumber;
	@OneToMany(targetEntity = RoomDetails.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "hotelId", referencedColumnName = "hotelId")
	@JMap
	private List<RoomDetails> roomDetails;
	
}
