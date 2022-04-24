package com.publicis.sapient.creditcardsystem.service.exception;

import java.io.Serializable;

public class InvalidDataException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = -6927374976176895168L;

    public InvalidDataException(String errorMessage){
        super(errorMessage);
    }
}
