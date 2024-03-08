package com.emirhanarici.customerservice.exception;

import org.springframework.http.HttpStatus;

public class CustomerNotFoundException extends RuntimeException {

    public static final HttpStatus STATUS = HttpStatus.NOT_FOUND;

    public CustomerNotFoundException(String message) {
        super(message);
    }
}
