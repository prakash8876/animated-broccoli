package com.matoshri.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;
import java.io.Serializable;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException
        extends RuntimeException
        implements Serializable {
    @Serial
    private static final long serialVersionUID = 1710960909213813729L;

    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
