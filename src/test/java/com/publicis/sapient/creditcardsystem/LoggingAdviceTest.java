package com.publicis.sapient.creditcardsystem;

import com.publicis.sapient.creditcardsystem.api.controller.aop.LoggingAdvice;
import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LoggingAdviceTest {

    @InjectMocks
    LoggingAdvice mockLoggingAdvice;

    @Mock
    ProceedingJoinPoint mockProceedingJoinPoint;

    @Test
    public void testApplicationPointCut(){
        mockLoggingAdvice.applicationPointCut();
    }

    @Test(expected = NullPointerException.class)
    public void testApplicationLoggerWithMockedJoinPoint() throws Throwable{
        Assert.assertNotNull(mockLoggingAdvice.applicationLogger(mockProceedingJoinPoint));
    }
}
