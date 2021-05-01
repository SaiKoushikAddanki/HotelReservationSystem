package com.koushik.reservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koushik.reservation.configuration.IFeignClientConfig;
import com.koushik.reservation.entity.ReservationDetails;
import com.koushik.reservation.model.Hotel;
import com.koushik.reservation.model.ReservationDetailsDto;
import com.koushik.reservation.repository.ReservationRepository;
import com.koushik.reservation.response.ApiResponse;
import com.koushik.reservation.utilities.ReservationDetailsUtility;

@Service
public class ReservationService implements IReservationService {

	@Autowired
	ReservationRepository reservationRepository;

	@Autowired
	IFeignClientConfig client;

	@Override
	public ReservationDetails bookHotelWithDetails(ReservationDetailsDto reservationDetailsDto) {
		ReservationDetails reservationDetails = new ReservationDetailsUtility().convert(reservationDetailsDto);
		ApiResponse<Hotel> response = client.getHotelDetailsById(reservationDetailsDto.getHotelId());
		reservationDetails.setHotelName(response.getData().getName());
		reservationDetails.setCost((response.getData().getRoomDetails().stream()
				.filter(r -> reservationDetailsDto.getRoomType().equals(r.getRoomtypes())).findAny().orElse(null)
				.getPrice()) * reservationDetailsDto.getNumberofDays());
		System.out.println(reservationDetails.toString());
		return reservationRepository.save(reservationDetails);
	}

}
