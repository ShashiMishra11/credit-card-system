package com.publicis.sapient.creditcardsystem.exceptionhandling;

import lombok.Data;

import java.util.Date;

/**
 * Responsible for generating
 * proper API error response for
 * exceptionHandler processing
 */
@Data
public class ErrorDetailBean {

    private final Date timeStamp;
    private final String status;
    private final String errorDetails;

    public ErrorDetailBean(Date timeStamp, String status, String errorDetails) {

        super();
        this.timeStamp = timeStamp;
        this.status = status;
        this.errorDetails = errorDetails;
    }

}
