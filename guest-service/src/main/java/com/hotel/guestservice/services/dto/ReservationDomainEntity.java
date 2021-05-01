package com.hotel.guestservice.services.dto;

import lombok.*;

import java.util.Date;
import java.util.Map;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDomainEntity {
   private Date date;
   private String hotelName;
}
