package com.publicis.sapient.creditcardsystem.exceptionhandling;

import com.publicis.sapient.creditcardsystem.service.ApplicationConstant;
import com.publicis.sapient.creditcardsystem.service.exception.InvalidDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

/**
 * This is custom exception handle
 * which provides meaning messages for
 * listed exception
 */
@ControllerAdvice
@RestController
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    /**
     * Responsible for handling error for improper card number
     * @param ex : Error message
     * @return : Error details - custom error message with code
     */
    @ExceptionHandler(NumberFormatException.class)
    public final ResponseEntity<Object> handleNumberFormatException(NumberFormatException ex){
        return new ResponseEntity<>(getErrorDetailBean(ApplicationConstant.CARD_NUMBER_NOT_NUMERIC, ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Responsible for handling Fatal error across the application
     * @param ex : Error message
     * @return : Error details - custom error message with code
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex) {

        return new ResponseEntity<>(getErrorDetailBean(ApplicationConstant.INTERNAL_SERVER_ERROR_CODE, ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Responsible for handling invalid card number
     * and those failed in Luhn check
     * @param ex : Error message
     * @return : Error details - custom error message with code
     */
    @ExceptionHandler
    public final ResponseEntity<Object> handleInvalidDataException(InvalidDataException ex){
        return new ResponseEntity<>(getErrorDetailBean(ApplicationConstant.INVALID_CARD_NUMBER, ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Responsible for error message formation
     * @param code
     * @param msg
     * @return : ErrorDetailBean
     */
    private ErrorDetailBean getErrorDetailBean(String code, String msg) {

        return new ErrorDetailBean(new Date(), code, msg);
    }
}
