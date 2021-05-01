package com.hotel.guestservice.handlers;

import com.hotel.guestservice.services.IGuestService;
import com.hotel.guestservice.services.dto.GuestCompleteDetailEntity;
import com.hotel.guestservice.services.dto.GuestServiceDomainEntity;
import com.netflix.discovery.EurekaClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/guestservice")
@CircuitBreaker(name = "guestCircuitBreaker", fallbackMethod = "remoteCallFail")
public class GuestServiceControllerImplementation implements GuestServiceController{
    @Autowired
    private IGuestService service;

    @Autowired
    private EurekaClient eurekaClient;

    @Value("%{spring.application.name:guest_service}")
    private String applicationName;

    @PostMapping(value="/register")
    public ResponseEntity<Object> registerGuest(@RequestBody GuestServiceDomainEntity newGuest) throws Exception {
            GuestCompleteDetailEntity registeredGuest = service.addGuestDetails(newGuest);
      log.info("Successfully created the guest = "+registeredGuest.getName());
        return ResponseEntity.status(HttpStatus.OK).body(registeredGuest);
    }

    @GetMapping("/guest")
    public ResponseEntity<Object> getGuestDetails(@RequestParam String name,@RequestParam String password) throws Exception {

            GuestCompleteDetailEntity completeGuestDetails = service.getCompleteGuestDetails(name,password);
            log.info("Successfully retrieved the details of guest = "+completeGuestDetails.getName());
            return ResponseEntity.status(HttpStatus.OK).body(completeGuestDetails);
    }

    @GetMapping("/reservation")
    public ResponseEntity<Object> getReservationDetails(@RequestParam String name,@RequestParam String password) throws Exception {

            GuestCompleteDetailEntity reservationDetails = service.getGuestReservationDetails(name,password);
            log.info("Successfully retrieved the reservation details of guest = "+reservationDetails.getName());
            return ResponseEntity.status(HttpStatus.OK).body(reservationDetails);

    }


    public String remoteCallFail(Exception e) {
        return "Guest Service not available please avoid transactions";
    }

    @RequestMapping("/instance")
    public String instanceByApplicationName() {
        return this.eurekaClient.getApplication(applicationName).getName();
    }

}
