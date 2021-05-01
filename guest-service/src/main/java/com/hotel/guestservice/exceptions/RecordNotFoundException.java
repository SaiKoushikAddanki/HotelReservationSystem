package com.hotel.guestservice.exceptions;

public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException(String errorMessage, Throwable err){
        super(errorMessage,err);
    }
}
