package com.hotel.guestservice.persistence.repository;

import com.hotel.guestservice.persistence.model.GuestEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends CrudRepository<GuestEntity, String> {

}
