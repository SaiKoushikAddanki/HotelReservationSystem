package com.koushik.hotel.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
import com.koushik.hotel.exceptions.RecordNotFoundException;
import com.koushik.hotel.model.HotelDto;
import com.koushik.hotel.model.RoomDetailsDto;
import com.koushik.hotel.repository.HotelRepository;
import com.koushik.hotel.utility.HotelUtility;

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
		when(hotelRepository.save(new HotelUtility().convert(hotelDto))).thenReturn(hotel);
		assertThat(hotelService.insertHotelRecord(hotelDto)).isEqualTo(hotel);
	}

	@Test
	void testModifyHotelRecord() {
		when(hotelRepository.save(hotel)).thenReturn(hotel);
		when(hotelRepository.findById(9)).thenReturn(Optional.ofNullable(hotel));
		assertThat(hotelService.modifyHotelRecord(9, hotelDto)).isEqualTo(hotel);

	}

	@Test
	void testModifyHotelRecordFailureCase() {
		int id = 10;
		when(hotelRepository.save(hotel)).thenReturn(hotel);
		when(hotelRepository.findById(id)).thenReturn(null);
		try {
			hotelService.modifyHotelRecord(10, hotelDto);
		} catch (Exception e) {
			assertThat(e).isInstanceOf(RecordNotFoundException.class);
		}

	}

	@Test
	void testDeleteHotelRecord() {
		try {
			when(hotelRepository.findById(ArgumentMatchers.anyInt())).thenReturn(null);
			hotelService.deleteHotelRecordById(21);
		} catch (Exception e) {
			assertThat(e).isInstanceOf(RecordNotFoundException.class).hasMessage("Record not found with ID:21");
		}
	}

	@Test
	void testDeleteHotelRecordSuccess() {
		when(hotelRepository.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.ofNullable(hotel));
		hotelService.deleteHotelRecordById(41);
		verify(hotelRepository, times(1)).deleteById(41);
	}

}
