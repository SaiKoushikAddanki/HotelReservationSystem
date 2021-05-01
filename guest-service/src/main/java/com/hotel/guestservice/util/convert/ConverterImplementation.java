package com.hotel.guestservice.util.convert;

import com.googlecode.jmapper.JMapper;
import com.googlecode.jmapper.api.JMapperAPI;
import com.hotel.guestservice.persistence.model.CreditCardEntity;
import com.hotel.guestservice.persistence.model.GuestEntity;
import com.hotel.guestservice.services.dto.GuestServiceDomainEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ConverterImplementation implements IConverter{
    JMapper<GuestEntity,GuestServiceDomainEntity> guestMapper;
    JMapper<CreditCardEntity,GuestServiceDomainEntity> cardMapper;
    public ConverterImplementation() {
        guestMapper = new JMapper<>(GuestEntity.class,GuestServiceDomainEntity.class);
        cardMapper = new JMapper<>(CreditCardEntity.class,GuestServiceDomainEntity.class);

    }

    @Override
    public GuestEntity convertGuest(GuestServiceDomainEntity guestServiceDomainEntity) {
        return guestMapper.getDestination(guestServiceDomainEntity);
    }

    @Override
    public CreditCardEntity convertCreditCard(GuestServiceDomainEntity guestServiceDomainEntity) {
        return cardMapper.getDestination(guestServiceDomainEntity);
    }
}
