package com.koushik.reservation.configuration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.koushik.reservation.model.Hotel;
import com.koushik.reservation.response.ApiResponse;

@FeignClient(url = "http://localhost:8091", name = "RESERVATION-CLIENT")
public interface IFeignClientConfig {

	@GetMapping("/hotel/{id}")
	public ApiResponse<Hotel> getHotelDetailsById(@PathVariable int id);

}
