package com.example.accounts.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = org.springframework.http.HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String resourceName, String fileName, String fieldValue) {
        super(String.format("%s with %s = %s not found", resourceName, fieldValue, fileName));
    }
}
