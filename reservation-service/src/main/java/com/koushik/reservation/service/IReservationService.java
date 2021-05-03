package com.koushik.reservation.service;

import com.koushik.reservation.entity.ReservationDetails;
import com.koushik.reservation.exceptions.RecordNotFoundException;
import com.koushik.reservation.model.ReservationDetailsDto;
import com.koushik.reservation.response.ApiResponse;

public interface IReservationService {

	public ApiResponse<ReservationDetails> bookHotelWithDetails(ReservationDetailsDto reservationDetailsDto) throws RecordNotFoundException;
}
