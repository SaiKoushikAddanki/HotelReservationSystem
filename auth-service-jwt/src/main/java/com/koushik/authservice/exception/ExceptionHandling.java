package com.koushik.authservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koushik.authservice.response.ApiResponse;

import io.jsonwebtoken.MalformedJwtException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ExceptionHandling {

    @ResponseBody
    @ExceptionHandler(value = {ServiceBusinessException.class})
    public ApiResponse<String> handleException(Exception ex){
    	//log.error(ex.toString());
        ApiResponse<String> response = new ApiResponse<>();
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		response.setMessage("An exception occured in the system and got Handled");
		response.setData(ex.getMessage());
        return response;
    }
    
    @ResponseBody
    @ExceptionHandler(value = {MalformedJwtException.class, Exception.class})
    public ApiResponse<Boolean> handleMalformedJwtException(Exception ex) {
    	log.info("::::::Entered into the handleMalformedJwtException methos ::::");
    	log.error(ex.toString() + "handleMalformedJwtException");
    	ApiResponse<Boolean> response = new ApiResponse<>();
		response.setStatus(HttpStatus.OK);
		response.setMessage("An exception occured in the system and got Handled");
		response.setData(false);
    	return response;
    }
}
