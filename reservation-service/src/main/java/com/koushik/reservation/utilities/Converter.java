package com.koushik.reservation.utilities;

import com.koushik.reservation.entity.ReservationDetails;
import com.koushik.reservation.model.ReservationDetailsDto;


public interface Converter {

	public ReservationDetails convert(ReservationDetailsDto reservationDetailsDto);
}
