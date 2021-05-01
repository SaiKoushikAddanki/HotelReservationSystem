package com.hotel.guestservice.persistence.model;


import com.googlecode.jmapper.annotations.JMap;
import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "guest_credit_card")
public class CreditCardEntity {
    @JMap
    @Id
    @Column(name = "name")
    private String name;
    @JMap
    @Column(name="password")
    private String password;
    @JMap
    @Column(name="card_number")
    private Long cardNumber;
    @JMap
    @Column(name="expiry")
    private String expiry;
    @JMap
    @Column(name="cvv")
    private Integer cvv;
    @JMap("CardOwner")
    @Column(name="card_owner")
    private String CardOwner;

}
