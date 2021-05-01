package com.hotel.guestservice.services;

import com.hotel.guestservice.services.dto.GuestCompleteDetailEntity;
import com.hotel.guestservice.services.dto.GuestServiceDomainEntity;

public interface IGuestService {
    public GuestCompleteDetailEntity addGuestDetails(GuestServiceDomainEntity guestServiceDomainEntity) throws Exception;
    public GuestCompleteDetailEntity getCompleteGuestDetails(String name, String password) throws Exception;
    public GuestCompleteDetailEntity getGuestReservationDetails(String name,String password) throws Exception;
}
