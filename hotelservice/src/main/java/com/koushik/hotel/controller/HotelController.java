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
import com.koushik.hotel.response.ApiResponse;
import com.koushik.hotel.service.IHotelService;

import javassist.NotFoundException;

@RestController
public class HotelController implements IHotelController {

	Logger logger = LoggerFactory.getLogger(HotelController.class);
	@Autowired
	IHotelService hotelService;

	@Override
	public ApiResponse<Hotel> addHotelRecord(@RequestBody HotelDto hotel) {
		logger.info("START::addHotelRecord method");
		ApiResponse<Hotel> response = new ApiResponse<>();
		response.setStatus(HttpStatus.CREATED);
		response.setMessage("Successfully inserted the record");
		response.setData(hotelService.insertHotelRecord(hotel));
		logger.info("END::addHotelRecord method");
		return response;
	}

	@Override
	public ApiResponse<Optional<Hotel>> getHotelDetails(@PathVariable int id) {
		logger.info("START::get Hotel Details method");
		ApiResponse<Optional<Hotel>> response = new ApiResponse<>();
		response.setStatus(HttpStatus.OK);
		response.setMessage("Successfully retrieved the record");
		response.setData(hotelService.getHotelDetailsById(id));
		logger.info("END::get Hotel Details method");
		return response;
	}

	@Override
	public ApiResponse<Hotel> modifyHotelRecord(@PathVariable int id, @RequestBody HotelDto hotel)
			throws NotFoundException {
		logger.info("START::Modify Hotel Records method");
		ApiResponse<Hotel> response = new ApiResponse<>();
		response.setStatus(HttpStatus.ACCEPTED);
		response.setMessage("Successfully updated the record");
		response.setData(hotelService.modifyHotelRecord(id, hotel));
		logger.info("END::Modify Hotel Records method");
		return response;
	}

	@Override
	public ApiResponse<String> deleteHotelRecord(@PathVariable int id) {
		logger.info("START::Delete Hotel Record method");
		ApiResponse<String> response = new ApiResponse<>();
		response.setStatus(HttpStatus.ACCEPTED);
		response.setMessage("Successfully deleted the record");
		response.setData(hotelService.deleteHotelRecordById(id));
		logger.info("END::Delete Hotel Records method");
		return response;
	}
}
