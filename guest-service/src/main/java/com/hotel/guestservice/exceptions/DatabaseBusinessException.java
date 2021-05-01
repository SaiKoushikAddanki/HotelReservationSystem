package com.hotel.guestservice.exceptions;

public class DatabaseBusinessException extends RuntimeException {
    public DatabaseBusinessException(String errorMessage, Throwable err){
        super(errorMessage,err);
    }
}
