package com.koushik.reservation.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.koushik.reservation.response.ApiResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(value = {RecordNotFoundException.class})
    public ApiResponse<String> handleRecordNotFoundException(RecordNotFoundException ex){
        log.error(ex.toString());
        ApiResponse<String> response = new ApiResponse<>();
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		response.setMessage("Record not found with the provided information");
		response.setData(ex.getMessage());
        return response;
    }

    @ExceptionHandler(value={DatabaseBusinessException.class})
    public ResponseEntity<Object> handleDatabaseBusinessException(DatabaseBusinessException ex){
        log.error(ex.toString());
        return new ResponseEntity<Object>("Error while processing request in database layer:\n "+ex.getLocalizedMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value ={ServiceBusinessException.class})
    public ResponseEntity<Object> handleServiceBusinessException(ServiceBusinessException ex){
        log.error(ex.toString());
        return new ResponseEntity<Object>("Error while processing request in service layer:\n "+ex.getLocalizedMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleException(Exception ex){
        log.error(ex.toString());
        return new ResponseEntity<Object>(ex.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
