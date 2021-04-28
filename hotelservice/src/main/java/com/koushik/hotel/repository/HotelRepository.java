package com.koushik.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.koushik.hotel.entity.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer>  {

}
