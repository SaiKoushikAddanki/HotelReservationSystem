package com.koushik.reservation.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.koushik.reservation.configuration.IFeignClientConfig;
import com.koushik.reservation.entity.ReservationDetails;
import com.koushik.reservation.exceptions.RecordNotFoundException;
import com.koushik.reservation.model.Hotel;
import com.koushik.reservation.model.ReservationDetailsDto;
import com.koushik.reservation.repository.ReservationRepository;
import com.koushik.reservation.response.ApiResponse;
import com.koushik.reservation.utilities.ReservationDetailsUtility;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class ReservationService implements IReservationService {
	
	Logger logger = LoggerFactory.getLogger(ReservationService.class);
	private static final String RESERVATION_SERVICE = "reservationservice";

	@Autowired
	ReservationRepository reservationRepository;

	@Autowired
	IFeignClientConfig client;

	@Override
	@CircuitBreaker(name = RESERVATION_SERVICE,fallbackMethod = "hotelServiceFallback")
	public ApiResponse<ReservationDetails> bookHotelWithDetails(ReservationDetailsDto reservationDetailsDto) throws RecordNotFoundException {
		ReservationDetails reservationDetails = new ReservationDetailsUtility().convert(reservationDetailsDto);
		ApiResponse<Hotel> response = client.getHotelDetailsById(reservationDetailsDto.getHotelId());
		reservationDetails.setHotelName(response.getData().getName());
		reservationDetails.setCost((response.getData().getRoomDetails().stream()
				.filter(r -> reservationDetailsDto.getRoomType().equals(r.getRoomtypes())).findAny().orElseThrow(RecordNotFoundException::new)
				.getPrice()) * reservationDetailsDto.getNumberofDays());
		ApiResponse<ReservationDetails> mainResponse = new ApiResponse<>();
		mainResponse.setStatus(HttpStatus.CREATED);
		mainResponse.setData(reservationRepository.save(reservationDetails));
		return mainResponse;
	}
	
	public ApiResponse<String> hotelServiceFallback(Exception ex) {
		logger.error("inside the circuit breaker fall back method.");
		ApiResponse<String> response = new ApiResponse<>();
		response.setStatus(HttpStatus.OK);
		response.setMessage("Hotel service is done now!!");
		response.setData(ex.getMessage());
		logger.error("end of the circuit breaker fall back method.");
		return response;
	}

}
