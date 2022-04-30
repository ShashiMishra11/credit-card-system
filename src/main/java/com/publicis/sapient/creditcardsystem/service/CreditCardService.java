package com.publicis.sapient.creditcardsystem.service;

import com.publicis.sapient.creditcardsystem.service.bean.CreditCardBean;
import com.sun.media.sound.InvalidDataException;

import java.util.List;

public interface CreditCardService {
    String registerNewCreditCard(CreditCardBean creditCardRequestObj) throws InvalidDataException;

    List<CreditCardBean> retrieveCreditCard();
}
