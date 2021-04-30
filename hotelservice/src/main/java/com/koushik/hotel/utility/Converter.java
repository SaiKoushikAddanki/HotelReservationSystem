package com.koushik.hotel.utility;

import com.koushik.hotel.entity.Hotel;
import com.koushik.hotel.model.HotelDto;


public interface Converter {

	public Hotel convert(HotelDto hotelDto);
}
