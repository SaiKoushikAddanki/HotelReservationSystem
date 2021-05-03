package com.koushik.apigateway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koushik.apigateway.response.ApiResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ExceptionHandling {

    @ResponseBody
    @ExceptionHandler(value = {Exception.class, ServiceBusinessException.class})
    public ApiResponse<String> handleException(Exception ex){
    	log.error(ex.toString());
        ApiResponse<String> response = new ApiResponse<>();
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		response.setMessage("An exception occured in the system and got Handled");
		response.setData(ex.getMessage());
        return response;
    }
}
