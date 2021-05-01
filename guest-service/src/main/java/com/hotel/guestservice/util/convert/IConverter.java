package com.hotel.guestservice.util.convert;

import com.hotel.guestservice.persistence.model.CreditCardEntity;
import com.hotel.guestservice.persistence.model.GuestEntity;
import com.hotel.guestservice.services.dto.GuestServiceDomainEntity;

public interface IConverter {
    public GuestEntity convertGuest(GuestServiceDomainEntity guestServiceDomainEntity);
    public CreditCardEntity convertCreditCard(GuestServiceDomainEntity guestServiceDomainEntity);
}
