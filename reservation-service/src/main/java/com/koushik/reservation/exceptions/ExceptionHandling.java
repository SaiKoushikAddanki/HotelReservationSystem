package com.koushik.reservation.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koushik.reservation.response.ApiResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ExceptionHandling {

	@ResponseBody
    @ExceptionHandler(value = {RecordNotFoundException.class})
    public ApiResponse<String> handleRecordNotFoundException(RecordNotFoundException ex){
        log.error(ex.toString());
        ApiResponse<String> response = new ApiResponse<>();
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		response.setMessage("Record not found with the provided information");
		response.setData(ex.getMessage());
        return response;
    }

	/*
	 * @ResponseBody
	 * 
	 * @ExceptionHandler(value={DatabaseBusinessException.class}) public
	 * ApiResponse<String> handleDatabaseBusinessException(DatabaseBusinessException
	 * ex){ log.error(ex.toString()); ApiResponse<String> response = new
	 * ApiResponse<>(); response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
	 * response.setMessage("Database related issue occured in the system");
	 * response.setData(ex.getMessage()); return response; }
	 */

	@ResponseBody
    @ExceptionHandler(value ={ServiceBusinessException.class})
    public ApiResponse<String> handleServiceBusinessException(ServiceBusinessException ex){
    	log.error(ex.toString());
        ApiResponse<String> response = new ApiResponse<>();
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		response.setMessage("Service business exception occured in the system");
		response.setData(ex.getMessage());
        return response;
    }

	@ResponseBody
    @ExceptionHandler(value = {Exception.class})
    public ApiResponse<String> handleException(Exception ex){
    	log.error(ex.toString());
        ApiResponse<String> response = new ApiResponse<>();
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		response.setMessage("An exception occured in the system and got Handled");
		response.setData(ex.getMessage());
        return response;
    }
}
