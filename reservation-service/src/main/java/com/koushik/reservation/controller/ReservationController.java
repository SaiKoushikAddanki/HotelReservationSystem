package com.koushik.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import com.koushik.reservation.entity.ReservationDetails;
import com.koushik.reservation.model.ReservationDetailsDto;
import com.koushik.reservation.response.ApiResponse;
import com.koushik.reservation.service.ReservationService;

@RestController
public class ReservationController implements IReservationController {

	@Autowired
	ReservationService reservationService;

	@Override
	public ApiResponse<ReservationDetails> bookHotelWithDetails(ReservationDetailsDto reservationDetailsDto) {
		ApiResponse<ReservationDetails> response = new ApiResponse<>();
		response.setData(reservationService.bookHotelWithDetails(reservationDetailsDto));
		response.setStatus(HttpStatus.CREATED);
		return response;
	}

	@Override
	public ApiResponse<ReservationDetails> getBookingDetails(int id) {
		return null;
	}

}
