package com.hotel.guestservice.persistence.model;

import com.googlecode.jmapper.annotations.JMap;
import com.hotel.guestservice.common.RoomTypes;
import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="guest_details")
public class GuestEntity {
    @JMap
    @Id
    @Column(name="name")
    private String name;
    @JMap
    @Column(name="number")
    private Long number;
    @JMap
    @Column(name="email")
    private String email;
    @JMap
    @Column(name="address")
    private String address;
    @JMap
    @Column(name="category")
    private RoomTypes category;
    @JMap
    @Column(name="password")
    private String password;


}
