package com.hotel.guestservice.persistence.repository;

import com.hotel.guestservice.persistence.model.CreditCardEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends CrudRepository<CreditCardEntity,String> {
}
