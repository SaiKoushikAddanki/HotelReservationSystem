package com.koushik.reservation.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RecordNotFoundException extends RuntimeException {

	static final long serialVersionUID = 1L;

	public RecordNotFoundException(String errorMessage, Throwable err){
        super(errorMessage,err);
    }
}
