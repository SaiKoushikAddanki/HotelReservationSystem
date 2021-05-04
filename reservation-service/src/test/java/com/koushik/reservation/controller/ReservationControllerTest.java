package com.koushik.reservation.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.koushik.reservation.entity.ReservationDetails;
import com.koushik.reservation.model.Hotel;
import com.koushik.reservation.model.ReservationDetailsDto;
import com.koushik.reservation.model.RoomDetails;
import com.koushik.reservation.model.RoomTypes;
import com.koushik.reservation.response.ApiResponse;
import com.koushik.reservation.service.ReservationService;
import com.koushik.reservation.utilities.ReservationDetailsUtility;

@WebMvcTest(ReservationController.class)
@ExtendWith(SpringExtension.class)
public class ReservationControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	ReservationService reservationService;
	
	private ReservationDetailsDto reservationDetailsDto;
	private ReservationDetails reservationDetails;
	private ApiResponse<Hotel> hotelResponse;
	
	@BeforeEach
	public void setUp() {
		reservationDetailsDto = new ReservationDetailsDto(12, "Roman", 12, RoomTypes.PREMIUM, new Date(), 4);
		reservationDetails = new ReservationDetailsUtility().convert(reservationDetailsDto);
		reservationDetails.setHotelName("Sai Surya Residency");
		reservationDetails.setCost(4500);
		hotelResponse = new ApiResponse<>();
		hotelResponse.setStatus(HttpStatus.OK);
		hotelResponse.setMessage("Extracted the hotel entity");
		List<RoomDetails> list = new ArrayList<RoomDetails>();
		list.add(new RoomDetails(2, RoomTypes.KING_BED, 20, 3500));
		Hotel hotel = new Hotel(4, "Kohinor", "Hyderabad", "A", 9963382556L, list);
		hotelResponse.setData(hotel);
	}
	
	@Test
	void testGetBookingDetails() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/reserve/0")).andExpect(status().isOk());
	}
	
	@Test
	void testBookHotelWithDetails() throws Exception {
		String jsonRequest = new ObjectMapper().writeValueAsString(reservationDetailsDto);
		mockMvc.perform(MockMvcRequestBuilders.post("/reserve").content(jsonRequest)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
	}

}
