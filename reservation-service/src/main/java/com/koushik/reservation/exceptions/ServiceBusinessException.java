package com.koushik.reservation.exceptions;

public class ServiceBusinessException extends RuntimeException{
    public ServiceBusinessException(String errorMessage,Throwable err){
        super(errorMessage,err);
    }
}
