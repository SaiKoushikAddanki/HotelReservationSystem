package com.koushik.reservation.controller;

import java.util.Optional;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.koushik.reservation.entity.ReservationDetails;
import com.koushik.reservation.model.ReservationDetailsDto;
import com.koushik.reservation.response.ApiResponse;

import io.swagger.annotations.Api;

@Api(value = "ReservationController", description = "REST API to perform CRUD operations related to Reservation details Entity")
@RestController
public interface IReservationController {

	@PostMapping("/reserve")
	public ApiResponse<ReservationDetails> bookHotelWithDetails(
			@RequestBody ReservationDetailsDto reservationDetailsDto) throws NotFoundException;

	@GetMapping("reserve/{id}")
	public ApiResponse<Optional<ReservationDetails>> getBookingDetails(@PathVariable int id);

}
