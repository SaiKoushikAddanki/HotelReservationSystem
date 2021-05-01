package com.hotel.guestservice.util.http;

import com.hotel.guestservice.services.dto.ReservationDomainEntity;
import org.springframework.stereotype.Component;

@Component
public class ReservationServiceClientFallback implements ReservationServiceClient {
    @Override
    public ReservationDomainEntity[] getReservationDetailsForUser(String name, String password) {
        return new ReservationDomainEntity[]{null};
    }
}
