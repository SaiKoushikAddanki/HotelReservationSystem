package com.koushik.hotel.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.koushik.hotel.entity.RoomTypes;
import com.koushik.hotel.model.HotelDto;
import com.koushik.hotel.model.RoomDetailsDto;
import com.koushik.hotel.service.HotelService;

@WebMvcTest(HotelController.class)
@ExtendWith(SpringExtension.class)
class HotelControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	HotelService hotelService;

	private HotelDto hotelDto;

	@BeforeEach
	public void setUp() {
		List<RoomDetailsDto> list = new ArrayList<RoomDetailsDto>();
		list.add(new RoomDetailsDto(RoomTypes.KING_BED, 20, 3500));
		list.add(new RoomDetailsDto(RoomTypes.PREMIUM, 5, 4800));
		hotelDto = new HotelDto("Sri Satya Sai", "Pune", "A", 9963382556L, list);
	}

	@Test
	void testGetHotelDetails() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/hotel/1")).andExpect(status().isOk());
	}

	@Test
	void testAddHotelDetails() throws Exception {

		String jsonRequest = new ObjectMapper().writeValueAsString(hotelDto);
		mockMvc.perform(MockMvcRequestBuilders.post("/hotel").content(jsonRequest)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
	}

	@Test
	void testModifyHotelRecord() throws Exception {

		String jsonRequest = new ObjectMapper().writeValueAsString(hotelDto);
		mockMvc.perform(MockMvcRequestBuilders.put("/hotel/31").content(jsonRequest)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
	}

	@Test
	void testDeleteHotelRecord() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/hotel/9")).andExpect(status().isOk());
	}
}
