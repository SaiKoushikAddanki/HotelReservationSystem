package com.koushik.reservation.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
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
	public ApiResponse<ReservationDetails> bookHotelWithDetails(ReservationDetailsDto reservationDetailsDto)
			throws NotFoundException {

		return reservationService.bookHotelWithDetails(reservationDetailsDto);
	}

	@Override
	public ApiResponse<Optional<ReservationDetails>> getBookingDetails(int id) {
		ApiResponse<Optional<ReservationDetails>> response = new ApiResponse<>();
		response.setStatus(HttpStatus.OK);
		response.setMessage("Record with id:" + id + "is retrieved successfully");
		response.setData(reservationService.getHotelReservationDetails(id));
		return response;
	}

}
