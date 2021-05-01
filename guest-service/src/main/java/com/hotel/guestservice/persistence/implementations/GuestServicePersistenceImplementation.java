package com.hotel.guestservice.persistence.implementations;

import com.hotel.guestservice.exceptions.DatabaseBusinessException;
import com.hotel.guestservice.persistence.IGuestServicePersistence;
import com.hotel.guestservice.persistence.model.CreditCardEntity;
import com.hotel.guestservice.persistence.model.GuestEntity;
import com.hotel.guestservice.persistence.repository.CreditCardRepository;
import com.hotel.guestservice.persistence.repository.GuestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class GuestServicePersistenceImplementation implements IGuestServicePersistence {

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Override
    public void saveGuestDetails(GuestEntity guestEntity) throws Exception {
       try{
           guestRepository.save(guestEntity);
           log.info("Successfully persisted the guest = "+guestEntity.getName());
       }catch(Exception ex){
           log.error(ex.toString());
           throw new DatabaseBusinessException(ex.getMessage(),ex);
       }

    }
    @Override
    public GuestEntity fetchGuestDetails(String name, String password) throws Exception {
        try{
        Optional optional=guestRepository.findById(name);
        log.info("Successfully retrieved the guest");
        return (GuestEntity) optional.get();}
        catch(Exception ex){
            log.error(ex.toString());
            throw new DatabaseBusinessException(ex.getMessage(),ex);
        }
    }

    @Override
    public void saveCreditCardDetails(CreditCardEntity creditCardEntity) throws Exception {
       try {
           creditCardRepository.save(creditCardEntity);
           log.info("Successfully persisted the credit card for guest = "+creditCardEntity.getName());
       }catch(Exception ex){
           log.error(ex.toString());
           throw new DatabaseBusinessException(ex.getMessage(),ex);
       }
    }

    @Override
    public CreditCardEntity fetchCreditCardDetails(String name, String password) throws Exception {
        try{
        Optional optional = creditCardRepository.findById(name);
        log.info("Successfully retrieved the credit card");
        return (CreditCardEntity) optional.get();}
        catch(Exception ex){
            log.error(ex.toString());
            throw new DatabaseBusinessException(ex.getMessage(),ex);
        }
    }
}
