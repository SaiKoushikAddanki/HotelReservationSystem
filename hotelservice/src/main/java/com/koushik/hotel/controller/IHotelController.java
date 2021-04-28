package com.koushik.hotel.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.koushik.hotel.entity.Hotel;
import com.koushik.hotel.model.HotelDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
@Api(value = "HotelController", description = "REST API to perform CRUD operations related to Hotel Entity")
@RestController
public interface IHotelController {

	@PostMapping("/hotel")
	@ApiOperation(value = "insert the hotel records into the system")
	public ResponseEntity<Hotel> addHotelRecord(@RequestBody HotelDto hotel);
	
	@GetMapping("/hotel/{id}")
	@ApiOperation(value = "To Retrieve hotel information from the system")
	public ResponseEntity<Optional<Hotel>> getHotelDetails(@PathVariable int id);
	
	@PutMapping("/hotel/{id}")
	@ApiOperation(value = "To edit the existing hotel records in the system")
	public ResponseEntity<Hotel> modifyHotelRecord(@PathVariable int id, @RequestBody HotelDto hotel) throws NotFoundException;
	
	@DeleteMapping("/hotel/{id}")
	@ApiOperation(value = "To Delete the hotel records from the system")
	public ResponseEntity<String> deleteHotelRecord(@PathVariable int id);
	
}
