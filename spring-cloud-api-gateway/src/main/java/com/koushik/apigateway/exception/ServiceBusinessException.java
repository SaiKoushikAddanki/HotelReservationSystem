package com.koushik.apigateway.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ServiceBusinessException extends RuntimeException{
    public ServiceBusinessException(String errorMessage){
        super(errorMessage);
    }
}
