package com.koushik.reservation.service;

import com.koushik.reservation.entity.ReservationDetails;
import com.koushik.reservation.exceptions.RecordNotFoundException;
import com.koushik.reservation.model.ReservationDetailsDto;

public interface IReservationService {

	public ReservationDetails bookHotelWithDetails(ReservationDetailsDto reservationDetailsDto) throws RecordNotFoundException;
}
