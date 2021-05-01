package com.hotel.guestservice.handlers;

import com.hotel.guestservice.services.dto.GuestServiceDomainEntity;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface GuestServiceController {
    public ResponseEntity<Object> registerGuest(@RequestBody GuestServiceDomainEntity newGuest) throws Exception;
    public ResponseEntity<Object> getGuestDetails(@RequestParam String name, @RequestParam String password) throws Exception;
    public ResponseEntity<Object> getReservationDetails(@RequestParam String name,@RequestParam String password) throws Exception;
    public String instanceByApplicationName();
}
