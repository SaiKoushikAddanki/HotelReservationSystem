package com.hotel.guestservice.persistence;

import com.hotel.guestservice.persistence.model.CreditCardEntity;
import com.hotel.guestservice.persistence.model.GuestEntity;

public interface IGuestServicePersistence {
    public void saveGuestDetails(GuestEntity guestEntity) throws Exception;
    public GuestEntity fetchGuestDetails(String name,String password) throws Exception;
    public void saveCreditCardDetails(CreditCardEntity creditCardEntity) throws Exception;
    public CreditCardEntity fetchCreditCardDetails(String name,String password) throws Exception;
}
