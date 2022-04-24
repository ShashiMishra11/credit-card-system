package com.publicis.sapient.creditcardsystem;

import com.publicis.sapient.creditcardsystem.exceptionhandling.ExceptionHandlerController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

@RunWith(MockitoJUnitRunner.class)
public class ExceptionHandlerControllerTest {

    @InjectMocks
    ExceptionHandlerController mockExceptionHandlerController;

    @Mock
    NumberFormatException mockNumberFormatException;

    @Mock
    Exception mockException;

    @Test
    public void testHandleNumberFormatException(){
        Assert.assertNotNull(mockExceptionHandlerController.
                handleNumberFormatException(mockNumberFormatException));
    }

    @Test
    public void testNumberFormatExceptionStatusCode(){
        Assert.assertEquals(HttpStatus.BAD_REQUEST, mockExceptionHandlerController.
                handleNumberFormatException(mockNumberFormatException).getStatusCode());
    }

    @Test
    public void testHandleAllException(){
        Assert.assertNotNull(mockExceptionHandlerController.
                handleAllException(mockException));
    }

    @Test
    public void testAllExceptionStatusCode(){
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, mockExceptionHandlerController.
                handleAllException(mockException).getStatusCode());
    }
}
