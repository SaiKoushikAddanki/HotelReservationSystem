package com.koushik.hotel.utility;

import com.googlecode.jmapper.JMapper;
import com.googlecode.jmapper.api.JMapperAPI;
import com.koushik.hotel.entity.Hotel;
import com.koushik.hotel.model.HotelDto;

public class HotelUtility implements Converter {

	JMapper<Hotel, HotelDto> jMapper;
	
	public HotelUtility() {
		JMapperAPI api = new JMapperAPI()
				.add(JMapperAPI.mappedClass(Hotel.class));
		
		jMapper = new JMapper<>(Hotel.class, HotelDto.class, api);
	}

	@Override
	public Hotel convert(HotelDto hotelDTO) {
		return jMapper.getDestination(hotelDTO);
	}

}
