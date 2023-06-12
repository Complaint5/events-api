package com.complaint5.academic_events.services.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DataBindingViolationException extends DataIntegrityViolationException {
    public DataBindingViolationException(String message){
        super(message);
    }
}
