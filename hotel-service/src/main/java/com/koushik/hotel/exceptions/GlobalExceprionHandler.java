package com.koushik.hotel.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koushik.hotel.response.ApiResponse;

import javassist.NotFoundException;

@ControllerAdvice
public class GlobalExceprionHandler {

	@ResponseBody
	@ExceptionHandler(RecordNotFoundException.class)
	public ApiResponse<Object> handleAllException(NotFoundException ex) {
		ApiResponse<Object> response = new ApiResponse<>();
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		response.setMessage(ex.getMessage());
		return response;
	}

}
