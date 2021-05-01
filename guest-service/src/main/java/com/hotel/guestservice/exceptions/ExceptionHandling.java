package com.hotel.guestservice.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(value = {RecordNotFoundException.class})
    public ResponseEntity<Object> handleRecordNotFoundException(RecordNotFoundException ex){
        log.error(ex.toString());
        return new ResponseEntity<Object>("RECORD NOT FOUND",HttpStatus.INTERNAL_SERVER_ERROR);
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
