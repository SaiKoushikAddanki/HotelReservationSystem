package com.koushik.reservation.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.koushik.reservation.configuration.IFeignClientConfig;
import com.koushik.reservation.entity.ReservationDetails;
import com.koushik.reservation.model.Hotel;
import com.koushik.reservation.model.ReservationDetailsDto;
import com.koushik.reservation.model.RoomDetails;
import com.koushik.reservation.model.RoomTypes;
import com.koushik.reservation.repository.ReservationRepository;
import com.koushik.reservation.response.ApiResponse;
import com.koushik.reservation.utilities.ReservationDetailsUtility;

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ReservationServiceTest {

	@MockBean
	private ReservationRepository reservationRepository;
	@MockBean
	private IFeignClientConfig client;

	@Autowired
	private ReservationService reservationService;
	@Autowired
	CircuitBreakerRegistry circuitBreakerRegistry;

	private ReservationDetailsDto reservationDetailsDto;
	@MockBean
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
		list.add(new RoomDetails(2, RoomTypes.PREMIUM, 20, 3500));
		Hotel hotel = new Hotel(4, "Kohinor", "Hyderabad", "A", 9963382556L, list);
		hotelResponse.setData(hotel);
	}

	@Test
	void testBookHotelWithDetailsFallBackCase() {
		circuitBreakerRegistry.circuitBreaker("reservationservice").reset();
		ApiResponse<ReservationDetails> response = new ApiResponse<>();
		response.setStatus(HttpStatus.CREATED);
		response.setData(reservationDetails);
		when(reservationRepository.save(reservationDetails)).thenReturn(reservationDetails);
		when(client.getHotelDetailsById(ArgumentMatchers.anyInt())).thenReturn(hotelResponse);
		assertThat(reservationService.bookHotelWithDetails(reservationDetailsDto).getMessage()).isEqualTo("Hotel service is done now!!");
	}
	
	@Test
	void testBookHotelWithDetails() {
		circuitBreakerRegistry.circuitBreaker("reservationservice").transitionToDisabledState();
		ApiResponse<ReservationDetails> response = new ApiResponse<>();
		response.setStatus(HttpStatus.CREATED);
		response.setData(reservationDetails);
		when(reservationRepository.save(reservationDetails)).thenReturn(reservationDetails);
		when(client.getHotelDetailsById(ArgumentMatchers.anyInt())).thenReturn(hotelResponse);
		assertThat(reservationService.bookHotelWithDetails(reservationDetailsDto).getStatus()).isEqualTo(response.getStatus());
	}
	
	@Test
	void testGetHotelReservationDetails() {
		int id=0;
		when(reservationRepository.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.ofNullable(reservationDetails));
		assertThat(reservationService.getHotelReservationDetails(id).get().getId()).isEqualTo(0);
	}
}
