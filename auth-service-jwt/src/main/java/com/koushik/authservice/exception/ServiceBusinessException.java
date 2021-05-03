package com.koushik.authservice.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ServiceBusinessException extends RuntimeException{
    public ServiceBusinessException(String errorMessage){
        super(errorMessage);
    }
}
