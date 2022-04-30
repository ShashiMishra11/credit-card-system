package com.publicis.sapient.creditcardsystem;

import com.publicis.sapient.creditcardsystem.repository.CreditCardRepository;
import com.publicis.sapient.creditcardsystem.repository.entity.CreditCardEntity;
import com.publicis.sapient.creditcardsystem.service.ApplicationConstant;
import com.publicis.sapient.creditcardsystem.service.Impl.CreditCardServiceImpl;
import com.publicis.sapient.creditcardsystem.service.bean.CreditCardBean;
import com.publicis.sapient.creditcardsystem.service.util.CardValidationUtil;
import com.sun.media.sound.InvalidDataException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CreditCardServiceTest {

    @InjectMocks
    CreditCardServiceImpl mockCreditCardServiceImpl;

    @Mock
    CreditCardRepository mockCreditCardRepository;

    @Mock
    CardValidationUtil mockCardValidationUtil;

    @Test
    public void testAddCreditCard() throws Exception{
        Assert.assertNotNull(mockCreditCardServiceImpl.
                registerCreditCard(new CreditCardBean("Test Card","4417123456789113",800l,
                        ApplicationConstant.DEFAULT_CREDIT_CARD_BALANCE)));
    }

    @Test
    public void testMoreThanAllowedCharacter() throws Exception{
        Assert.assertEquals(ApplicationConstant.ERROR_CARD_NUMBER_MAX_LENGTH, mockCreditCardServiceImpl.
                registerCreditCard(new CreditCardBean("Test Card","441712345678911345677899",800l,
                        ApplicationConstant.DEFAULT_CREDIT_CARD_BALANCE)));
    }

    @Test(expected = InvalidDataException.class)
    public void testInvalidCardNumber() throws InvalidDataException {
        Assert.assertEquals(ApplicationConstant.INVALID_CARD_NUMBER, mockCreditCardServiceImpl.
                registerCreditCard(new CreditCardBean("Test Card","4417123456",800l,
                        ApplicationConstant.DEFAULT_CREDIT_CARD_BALANCE)));
    }

    @Test
    public void testGetAllCreditCard(){
        Assert.assertNotNull(mockCreditCardServiceImpl.retrieveCreditCard());
    }

    private CreditCardEntity getCreditCardEntityObj(){
        CreditCardEntity creditCardEntity = new CreditCardEntity();
        creditCardEntity.setName("Test Card");
        creditCardEntity.setCardNumber("4417123456789113");
        creditCardEntity.setLimit(700l);
        creditCardEntity.setBalance(ApplicationConstant.DEFAULT_CREDIT_CARD_BALANCE);
        return creditCardEntity;
    }
}
