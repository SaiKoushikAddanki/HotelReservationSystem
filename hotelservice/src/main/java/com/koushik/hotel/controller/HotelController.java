package com.koushik.hotel.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.koushik.hotel.entity.Hotel;
import com.koushik.hotel.model.HotelDto;
import com.koushik.hotel.service.IHotelService;

import javassist.NotFoundException;

@RestController
public class HotelController implements IHotelController {

	Logger logger = LoggerFactory.getLogger(HotelController.class);
	@Autowired
	IHotelService hotelService;

	@Override
	public ResponseEntity<Hotel> addHotelRecord(@RequestBody HotelDto hotel) {
		logger.info("Start::addHotelRecord method");
		return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.insertHotelRecord(hotel));
	}

	@Override
	public ResponseEntity<Optional<Hotel>> getHotelDetails(@PathVariable int id) {
		logger.info("Start::addHotelRecord method");
		return ResponseEntity.ok(hotelService.getHotelDetailsById(id));
	}

	@Override
	public ResponseEntity<Hotel> modifyHotelRecord(@PathVariable int id, @RequestBody HotelDto hotel)
			throws NotFoundException {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(hotelService.modifyHotelRecord(id, hotel));
	}

	@Override
	public ResponseEntity<String> deleteHotelRecord(@PathVariable int id) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(hotelService.deleteHotelRecordById(id));
	}
}
