package com.publicis.sapient.creditcardsystem;

import com.publicis.sapient.creditcardsystem.api.controller.CreditCardController;
import com.publicis.sapient.creditcardsystem.service.ApplicationConstant;
import com.publicis.sapient.creditcardsystem.service.CreditCardService;
import com.publicis.sapient.creditcardsystem.service.bean.CreditCardBean;
import com.sun.media.sound.InvalidDataException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreditCardControllerTest {

    @InjectMocks
    CreditCardController mockCreditCardController;

    @Mock
    CreditCardService mockCreditCardService;

    @Test
    public void testAddCreditCard(){
        Assert.assertNotNull(mockCreditCardController.
                registerCreditCard(new CreditCardBean("Test Card","4417123456789113",800l,
                        ApplicationConstant.DEFAULT_CREDIT_CARD_BALANCE)));
    }

    @Test
    public void testInvalidCardNumber() throws Exception{
        when(mockCreditCardService.registerCreditCard(new CreditCardBean("Test Card","44171234567Rsfdjhj",800l,
                ApplicationConstant.DEFAULT_CREDIT_CARD_BALANCE))).thenThrow(NumberFormatException.class);
        Assert.assertNotNull(mockCreditCardController.
                registerCreditCard(new CreditCardBean("Test Card","44171234567Rsfdjhj",800l,
                        ApplicationConstant.DEFAULT_CREDIT_CARD_BALANCE)));
    }

    @Test
    public void testAddCardThrowNumberFormatException() throws InvalidDataException {
        when(mockCreditCardService.registerCreditCard(new CreditCardBean("Test Card","44171234567Rsfdjhj",800l,
                ApplicationConstant.DEFAULT_CREDIT_CARD_BALANCE))).thenThrow(NumberFormatException.class);
        Assert.assertNotNull(mockCreditCardController.registerCreditCard(new CreditCardBean("Test Card","44171234567Rsfdjhj",800l,
                ApplicationConstant.DEFAULT_CREDIT_CARD_BALANCE)));
    }

    @Test
    public void testAddCardThrowInvalidDataException() {
        Assert.assertNotNull(mockCreditCardController.registerCreditCard(new CreditCardBean("Test Card","44171234567",800l,
                ApplicationConstant.DEFAULT_CREDIT_CARD_BALANCE)));
    }

    @Test
    public void testGetAllCreditCard(){
        Assert.assertNotNull(mockCreditCardController.retrieveCreditCard());
    }

    @Test(expected = Exception.class)
    public void testGetAllCardException(){
        when(mockCreditCardService.retrieveCreditCard()).thenThrow(Exception.class);
        Assert.assertNotNull(mockCreditCardController.retrieveCreditCard());
    }
}
