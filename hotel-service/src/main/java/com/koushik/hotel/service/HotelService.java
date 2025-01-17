package com.koushik.hotel.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koushik.hotel.entity.Hotel;
import com.koushik.hotel.exceptions.RecordNotFoundException;
import com.koushik.hotel.model.HotelDto;
import com.koushik.hotel.repository.HotelRepository;
import com.koushik.hotel.utility.HotelUtility;

import javassist.NotFoundException;

@Service
public class HotelService implements IHotelService {

	Logger logger = LoggerFactory.getLogger(HotelService.class);

	@Autowired
	HotelRepository hotelRepository;

	@Override
	public Optional<Hotel> getHotelDetailsById(int id) {
		logger.info("START::get Hotel Record method");
		Optional<Hotel> hotel = hotelRepository.findById(id);
		logger.info("END::get Hotel Record method");
		return hotel;
	}

	@Override
	public Hotel insertHotelRecord(HotelDto hotelDto) {
		logger.info("START::insert Hotel Record method");
		return hotelRepository.save(new HotelUtility().convert(hotelDto));
	}

	@Override
	public Hotel modifyHotelRecord(int id, HotelDto hotelDto) throws RecordNotFoundException {
		logger.info("START::modify Hotel Record method");
		if (hotelRepository.findById(id) != null) {
			logger.info("Inside IF condition as the recoed is present with ID:{}", id);
			return hotelRepository.save(new HotelUtility().convert(hotelDto));
		} else {
			logger.trace("In ELSE block as the record is not present with ID:{}", id);
			throw new RecordNotFoundException("Reocrd not found with ID:" + id);
		}
	}

	@Override
	public String deleteHotelRecordById(int id) throws RecordNotFoundException {
		logger.info("START::Delete Hotel Record method");
		if (hotelRepository.findById(id) != null) {
			logger.info("Inside IF condition as the recoed is present with ID:{}", id);
			hotelRepository.deleteById(id);
			return "Delete successful for ID:" + id;
		} else {
			logger.trace("In ELSE block as the record is not present with ID:{}", id);
			throw new RecordNotFoundException("Record not found with ID:" + id); 
		}
	}

}
