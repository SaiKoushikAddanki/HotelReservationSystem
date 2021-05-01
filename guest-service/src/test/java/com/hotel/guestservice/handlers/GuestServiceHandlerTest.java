package com.hotel.guestservice.handlers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotel.guestservice.common.RoomTypes;
import com.hotel.guestservice.services.IGuestService;
import com.hotel.guestservice.services.dto.GuestCompleteDetailEntity;
import com.hotel.guestservice.services.dto.GuestServiceDomainEntity;

import com.netflix.discovery.EurekaClient;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(GuestServiceControllerImplementation.class)
public class GuestServiceHandlerTest {

    @MockBean
    private IGuestService service;

    @MockBean
    private EurekaClient eurekaClient;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void registerGuestTest() throws Exception {

        GuestServiceDomainEntity guestServiceDomainEntity = new GuestServiceDomainEntity("Akshaya", 123456789L,
                "abc@xyz.com","Pune,Maharashtra", RoomTypes.DOUBLE_QUEEN,"abcdef",123456789101L,"12/24",001,"Ak");
        GuestCompleteDetailEntity guestCompleteDetailEntity = new GuestCompleteDetailEntity("Akshaya", 123456789L,
                "abc@xyz.com","Pune,Maharashtra", RoomTypes.DOUBLE_QUEEN,"abcdef",123456789101L,"12/24",001,"Ak","loyal",null);
        when(service.addGuestDetails(any())).thenReturn(guestCompleteDetailEntity);
        String jsonRequest = new ObjectMapper().writeValueAsString(guestServiceDomainEntity);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/guestservice/register").content(jsonRequest).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
  @Test
    public void registerGuestFailureTest() throws Exception {

        GuestServiceDomainEntity guestServiceDomainEntity = new GuestServiceDomainEntity("Akshaya", 123456789L,
                "abc@xyz.com","Pune,Maharashtra", RoomTypes.DOUBLE_QUEEN,"abcdef",123456789101L,"12/24",001,"Ak");
        String jsonRequest = new ObjectMapper().writeValueAsString(guestServiceDomainEntity);
        when(service.addGuestDetails(any())).thenThrow(new Exception("Unable to register"));
        this.mockMvc.perform(MockMvcRequestBuilders.post("/guestservice/register").content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }


    @Test
    public void getGuestDetailsTest() throws Exception {

        GuestCompleteDetailEntity guestCompleteDetailEntity = new GuestCompleteDetailEntity("Akshaya", 123456789L,
                "abc@xyz.com","Pune,Maharashtra", RoomTypes.DOUBLE_QUEEN,"abcdef",123456789101L,"12/24",001,"Ak","loyal",null);
        when(service.getCompleteGuestDetails("Akshaya", "abcdef")).thenReturn(guestCompleteDetailEntity);
        this.mockMvc.perform(get("/guestservice/guest?name=Akshaya&password=abcdef")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json("{'name':'Akshaya','number':123456789,'email':'abc@xyz.com','address':'Pune,Maharashtra','category':'DOUBLE_QUEEN','password':'abcdef','cardNumber':123456789101,'expiry':'12/24','cvv':1,'loyalty':'loyal','stayHistory':null,'cardOwner':'Ak'}"));
    }

    @Test
    public void getGuestDetailsFailureTest() throws Exception {
        when(service.getCompleteGuestDetails("Akshaya","abcdef")).thenThrow(new Exception("No Valid guest"));
        this.mockMvc.perform(get("/guestservice/guest?name=Akshaya&password=abcdef")).andDo(print()).andExpect(status().is5xxServerError());
    }

    @Test
    public void getReservationDetailsTest() throws Exception {
        GuestCompleteDetailEntity guestCompleteDetailEntity = new GuestCompleteDetailEntity("Akshaya", 123456789L,
                "abc@xyz.com","Pune,Maharashtra", RoomTypes.DOUBLE_QUEEN,"abcdef",123456789101L,"12/24",001,"Ak","loyal",null);
        when(service.getGuestReservationDetails("Akshaya","abcdef")).thenReturn(guestCompleteDetailEntity);
        this.mockMvc.perform(get("/guestservice/reservation?name=Akshaya&password=abcdef")).andDo(print()).andExpect(status().isOk())
              .andExpect(content().json("{'name':'Akshaya','number':123456789,'email':'abc@xyz.com','address':'Pune,Maharashtra','category':'DOUBLE_QUEEN','password':'abcdef','cardNumber':123456789101,'expiry':'12/24','cvv':1,'loyalty':'loyal','stayHistory':null,'cardOwner':'Ak'}"));
    }

   @Test
    public void getReservationDetailsFailureTest() throws Exception {
        when(service.getGuestReservationDetails("Akshaya","abcdef")).thenThrow(new Exception("No record Found"));
        this.mockMvc.perform(get("/guestservice/reservation?name=Akshaya&password=abcdef")).andDo(print()).andExpect(status().is5xxServerError());
    }

}
