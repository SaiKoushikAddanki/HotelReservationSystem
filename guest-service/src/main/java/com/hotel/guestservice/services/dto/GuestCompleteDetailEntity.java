package com.hotel.guestservice.services.dto;

import com.hotel.guestservice.common.RoomTypes;
import lombok.*;

import java.util.Date;
import java.util.Map;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GuestCompleteDetailEntity {
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
    private String loyalty;
    private Map<Date,String> stayHistory;

}
