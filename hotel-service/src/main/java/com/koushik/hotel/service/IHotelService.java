package com.koushik.hotel.service;

import java.util.Optional;

import com.koushik.hotel.entity.Hotel;
import com.koushik.hotel.exceptions.RecordNotFoundException;
import com.koushik.hotel.model.HotelDto;

import javassist.NotFoundException;

public interface IHotelService {

	public Optional<Hotel> getHotelDetailsById(int id);

	public Hotel insertHotelRecord(HotelDto hotel);

	public Hotel modifyHotelRecord(int id, HotelDto hotel) throws RecordNotFoundException;

	public String deleteHotelRecordById(int id) throws RecordNotFoundException;
}
