package com.koushik.hotel.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koushik.hotel.entity.Hotel;
import com.koushik.hotel.model.HotelDto;
import com.koushik.hotel.repository.HotelRepository;
import com.koushik.hotel.utility.HotelUtility;

import javassist.NotFoundException;

@Service
public class HotelService implements IHotelService {

	@Autowired
	HotelRepository hotelRepository;
	
	
	@Override
	public Optional<Hotel> getHotelDetailsById(int id) {
		return hotelRepository.findById(id);
	}

	@Override
	public Hotel insertHotelRecord(HotelDto hotel) {
		return hotelRepository.save(new HotelUtility().convert(hotel));
	}

	@Override
	public Hotel modifyHotelRecord(int id, HotelDto hotel) throws NotFoundException {
		if (hotelRepository.findById(id).isPresent()) {
			return hotelRepository.save(new HotelUtility().convert(hotel));
		} else {
			throw new NotFoundException("Reocrd not found with ID:" + id);
		}
	}
	
	@Override
	public String deleteHotelRecordById(int id) {
		if(hotelRepository.findById(id).isPresent()) {
			hotelRepository.deleteById(id);
			return "Delete successful for ID:" + id;
		} else {
			return "Record not found with ID:" + id;
		}
	}

}
