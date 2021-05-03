package com.koushik.hotel.exceptions;

public class RecordNotFoundException extends RuntimeException {

	static final long serialVersionUID = 1L;

	public RecordNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
