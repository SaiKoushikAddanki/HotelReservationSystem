package com.koushik.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.koushik.reservation.entity.ReservationDetails;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationDetails, String> {

}
