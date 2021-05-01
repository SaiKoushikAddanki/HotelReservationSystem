package com.koushik.reservation.service;

import com.koushik.reservation.entity.ReservationDetails;
import com.koushik.reservation.model.ReservationDetailsDto;

public interface IReservationService {

	public ReservationDetails bookHotelWithDetails(ReservationDetailsDto reservationDetailsDto);
}
