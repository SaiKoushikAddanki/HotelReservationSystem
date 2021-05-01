package com.hotel.guestservice.services.implementations;

import com.hotel.guestservice.exceptions.RecordNotFoundException;
import com.hotel.guestservice.exceptions.ServiceBusinessException;
import com.hotel.guestservice.persistence.IGuestServicePersistence;
import com.hotel.guestservice.persistence.model.CreditCardEntity;
import com.hotel.guestservice.persistence.model.GuestEntity;
import com.hotel.guestservice.services.IGuestService;
import com.hotel.guestservice.services.dto.GuestCompleteDetailEntity;
import com.hotel.guestservice.services.dto.GuestServiceDomainEntity;
import com.hotel.guestservice.services.dto.ReservationDomainEntity;
import com.hotel.guestservice.util.convert.IConverter;
import com.hotel.guestservice.util.http.ReservationServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

@Slf4j
@Service
public class GuestServiceImplementation implements IGuestService {

    private final int REGULARITY=3;
    private final String LOYAL="loyal";
    private final String NOTLOYAL = "not loyal";

    @Autowired
    private IGuestServicePersistence persistence;

    @Autowired
    private ReservationServiceClient requestHandler;

    @Autowired
   private IConverter converter;

    @Override
    public GuestCompleteDetailEntity addGuestDetails(GuestServiceDomainEntity guestServiceDomainEntity) throws Exception {
       try{
        GuestEntity guestEntity = converter.convertGuest(guestServiceDomainEntity);
        CreditCardEntity cardEntity = converter.convertCreditCard(guestServiceDomainEntity);
        log.trace(guestServiceDomainEntity.toString());
        log.trace(guestEntity.toString());
        log.trace(cardEntity.toString());
        persistence.saveGuestDetails(guestEntity);
        persistence.saveCreditCardDetails(cardEntity);
        GuestCompleteDetailEntity completeDetailEntity = new GuestCompleteDetailEntity();
        log.info("Successfully created the guest = "+guestEntity.getName());
        return populateCompleteGuestDetails(completeDetailEntity,guestEntity,cardEntity);}
       catch(Exception ex){
           log.error(ex.toString());
           throw new ServiceBusinessException(ex.getMessage(),ex);
       }
    }

    @Override
    public GuestCompleteDetailEntity getCompleteGuestDetails(String name, String password) throws Exception {
       try{
        GuestCompleteDetailEntity completeDetailEntity = new GuestCompleteDetailEntity();
        Map<Date,String> history = new TreeMap<Date,String>();
        completeDetailEntity.setLoyalty(NOTLOYAL);
           GuestEntity guestEntity=persistence.fetchGuestDetails(name, password);
           CreditCardEntity cardEntity = persistence.fetchCreditCardDetails(name, password);
           ReservationDomainEntity[] reservationdetails=requestHandler.getReservationDetailsForUser(name, password);
           if(guestEntity==null || cardEntity == null || reservationdetails[0]==null){
               throw new RecordNotFoundException("RecordNOtFound",new Exception());
           }
           log.trace(guestEntity.toString());
           log.trace(cardEntity.toString());
        if(reservationdetails.length>REGULARITY){
            completeDetailEntity.setLoyalty(LOYAL);
        }
        for(ReservationDomainEntity reservation:reservationdetails){
            history.put(reservation.getDate(),reservation.getHotelName());
            log.trace(reservation.toString());
        }
        completeDetailEntity.setStayHistory(history);
        log.info("Successfully retrieved the details of guest "+guestEntity.getName());
        return populateCompleteGuestDetails(completeDetailEntity,guestEntity,cardEntity);}
       catch(Exception ex){
           log.error(ex.toString());
           throw new ServiceBusinessException(ex.getMessage(),ex);
       }
    }

    @Override
    public GuestCompleteDetailEntity getGuestReservationDetails(String name, String password) throws Exception {
        try{
            GuestCompleteDetailEntity completeDetailEntity = new GuestCompleteDetailEntity();
        Map<Date,String> history = new TreeMap<Date,String>();
        completeDetailEntity.setLoyalty(NOTLOYAL);
            GuestEntity guestEntity=persistence.fetchGuestDetails(name, password);
            CreditCardEntity cardEntity = persistence.fetchCreditCardDetails(name, password);
            ReservationDomainEntity[] reservationdetails=requestHandler.getReservationDetailsForUser(name, password);
            if(guestEntity==null || cardEntity == null || reservationdetails[0]==null){
                throw new RecordNotFoundException("RecordNOtFound",new Exception());
            }
            log.trace(guestEntity.toString());
            log.trace(cardEntity.toString());
        if(reservationdetails.length>REGULARITY){
            completeDetailEntity.setLoyalty(LOYAL);
        }
        for(ReservationDomainEntity reservation:reservationdetails){
            history.put(reservation.getDate(),reservation.getHotelName());
            log.trace(reservation.toString());
        }
        completeDetailEntity.setStayHistory(history);
            log.info("Successfully retrieved the reservation details of guest "+guestEntity.getName());
        return populateCompleteGuestDetails(completeDetailEntity,guestEntity,cardEntity);}
        catch(Exception ex){
            log.error(ex.toString());
            throw new ServiceBusinessException(ex.getMessage(),ex);
        }
    }

    public GuestCompleteDetailEntity populateCompleteGuestDetails(GuestCompleteDetailEntity completeDetailEntity,GuestEntity guestEntity,CreditCardEntity cardEntity){
        completeDetailEntity.setName(guestEntity.getName());
        completeDetailEntity.setNumber(guestEntity.getNumber());
        completeDetailEntity.setEmail(guestEntity.getEmail());
        completeDetailEntity.setAddress(guestEntity.getAddress());
        completeDetailEntity.setPassword(guestEntity.getPassword());
        completeDetailEntity.setCategory(guestEntity.getCategory());
       completeDetailEntity.setCardNumber(cardEntity.getCardNumber());
       completeDetailEntity.setExpiry(cardEntity.getExpiry());
       completeDetailEntity.setCvv(cardEntity.getCvv());
       completeDetailEntity.setCardOwner(cardEntity.getCardOwner());
        return completeDetailEntity;
    }
}
