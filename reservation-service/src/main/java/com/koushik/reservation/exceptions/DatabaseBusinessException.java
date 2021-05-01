package com.koushik.reservation.exceptions;

public class DatabaseBusinessException extends RuntimeException {
    public DatabaseBusinessException(String errorMessage, Throwable err){
        super(errorMessage,err);
    }
}
