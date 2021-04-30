package com.koushik.hotel;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.koushik.hotel.entity.Hotel;
import com.koushik.hotel.entity.RoomTypes;
import com.koushik.hotel.model.HotelDto;
import com.koushik.hotel.model.RoomDetailsDto;
import com.koushik.hotel.response.ApiResponse;
import com.koushik.hotel.utility.HotelUtility;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class HotelserviceApplicationTests {

	@Autowired
	private TestRestTemplate testRestTemplate;

	private HotelDto hotelDto;
	private Hotel hotel;

	@BeforeEach
	public void setUp() {
		List<RoomDetailsDto> list = new ArrayList<RoomDetailsDto>();
		list.add(new RoomDetailsDto(RoomTypes.KING_BED, 20, 3500));
		list.add(new RoomDetailsDto(RoomTypes.PREMIUM, 5, 4800));
		hotelDto = new HotelDto("Sri Satya Sai", "Pune", "A", 9963382556L, list);
		hotel = new HotelUtility().convert(hotelDto);
	}

	@Test
	void testGetHotelDetails() {
		ResponseEntity<ApiResponse> entity = testRestTemplate.getForEntity("/hotel/8", ApiResponse.class);
		assertThat(entity.getBody().getMessage()).isEqualTo("Successfully retrieved the record");
	}

	@Test
	void testAddRecord() {

		HttpEntity<HotelDto> entity = new HttpEntity<HotelDto>(hotelDto);
		System.out.println(entity.toString());
		ResponseEntity<ApiResponse> responseEntity = testRestTemplate.exchange("/hotel", HttpMethod.POST, entity,
				ApiResponse.class);
		assertThat(responseEntity.getBody().getStatus()).isEqualTo(HttpStatus.CREATED);
	}

	@Test
	void testModifyHotelRecord() {
		HttpEntity<HotelDto> entity = new HttpEntity<HotelDto>(hotelDto);
		ResponseEntity<ApiResponse> responseEntity = testRestTemplate.exchange("/hotel/5", HttpMethod.PUT, entity,
				ApiResponse.class);
		assertThat(responseEntity.getBody().getStatus()).isEqualTo(HttpStatus.ACCEPTED);
	}

	@Test
	void testDeleteHotelRecord() {
		testRestTemplate.execute("/hotel/50", HttpMethod.DELETE, null, null);
		ResponseEntity<ApiResponse> entity = testRestTemplate.getForEntity("/hotel/50", ApiResponse.class);
		assertThat(entity.getBody().getData()).isNull();
	}

}
