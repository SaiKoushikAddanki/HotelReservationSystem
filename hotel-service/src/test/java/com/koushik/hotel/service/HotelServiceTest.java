package com.koushik.hotel.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.koushik.hotel.entity.Hotel;
import com.koushik.hotel.entity.RoomTypes;
import com.koushik.hotel.model.HotelDto;
import com.koushik.hotel.model.RoomDetailsDto;
import com.koushik.hotel.repository.HotelRepository;
import com.koushik.hotel.utility.HotelUtility;

import javassist.NotFoundException;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class HotelServiceTest {

	@MockBean
	HotelRepository hotelRepository;

	@Autowired
	HotelService hotelService;

	private Hotel hotel;
	private HotelDto hotelDto;

	@BeforeEach
	public void setUp() {
		List<RoomDetailsDto> list = new ArrayList<RoomDetailsDto>();
		list.add(new RoomDetailsDto(RoomTypes.KING_BED, 20, 3500));
		hotelDto = new HotelDto("Kohinor", "Hyderabad", "A", 9963382556L, list);
		hotel = new HotelUtility().convert(hotelDto);

	}

	@Test
	void testGetHotelDetailsById() {
		int id = 1;
		when(hotelRepository.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.ofNullable(hotel));
		assertThat(hotelService.getHotelDetailsById(id).get().getLocation()).isEqualTo("Hyderabad");
	}

	@Test
	void testAddHotelDetails() {

		when(hotelRepository.save(hotel)).thenReturn(hotel);
		assertThat(hotelService.insertHotelRecord(hotelDto)).isEqualTo(hotel);
	}

	@Test
	void testModifyHotelRecord() throws NotFoundException {

		when(hotelRepository.save(hotel)).thenReturn(hotel);
		when(hotelRepository.findById(9)).thenReturn(Optional.ofNullable(hotel));
		assertThat(hotelService.modifyHotelRecord(9, hotelDto)).isEqualTo(hotel);
	}

	@Test
	void testDeleteHotelRecord() {
		assertThat(hotelService.deleteHotelRecordById(21)).isEqualTo("Record not found with ID:21");
	}

}
