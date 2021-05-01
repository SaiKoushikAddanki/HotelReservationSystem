package com.hotel.guestservice.util.http;

import com.hotel.guestservice.services.dto.ReservationDomainEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "reservationclient", url = "http://localhost:8081/resrvationservice")
public interface ReservationServiceClient {

    @RequestMapping(method = RequestMethod.GET, value = "/guest")
    ReservationDomainEntity[] getReservationDetailsForUser(@RequestParam(name="name")String name,@RequestParam(name="password") String password);
}