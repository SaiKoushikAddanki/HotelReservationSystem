package com.hotel.guestservice.services.dto;

import com.hotel.guestservice.common.RoomTypes;
import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GuestServiceDomainEntity {
    private String name;
    private Long number;
    private String email;
    private String address;
    private RoomTypes category;
    private String password;
    private Long cardNumber;
    private String expiry;
    private Integer cvv;
    private String CardOwner;
}
