package com.koushik.reservation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.koushik.reservation.entity.ReservationDetails;
import com.koushik.reservation.model.ReservationDetailsDto;
import com.koushik.reservation.response.ApiResponse;

import io.swagger.annotations.Api;

@Api(value = "ReservationController", description = "REST API to perform CRUD operations related to Hotel Entity")
@RestController
public interface IReservationController {

	@PostMapping("/reserve")
	public ApiResponse<ReservationDetails> bookHotelWithDetails(
			@RequestBody ReservationDetailsDto reservationDetailsDto);

	@GetMapping("reserve/{id}")
	public ApiResponse<ReservationDetails> getBookingDetails(@PathVariable int id);

}
