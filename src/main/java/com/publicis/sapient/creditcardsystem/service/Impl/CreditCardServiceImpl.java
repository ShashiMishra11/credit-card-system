package com.publicis.sapient.creditcardsystem.service.Impl;

import com.publicis.sapient.creditcardsystem.repository.CreditCardRepository;
import com.publicis.sapient.creditcardsystem.repository.entity.CreditCardEntity;
import com.publicis.sapient.creditcardsystem.service.ApplicationConstant;
import com.publicis.sapient.creditcardsystem.service.CreditCardService;
import com.publicis.sapient.creditcardsystem.service.bean.CreditCardBean;
import com.publicis.sapient.creditcardsystem.service.util.CardValidationUtil;
import com.sun.media.sound.InvalidDataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for fetching
 * data from table via a repository object
 */
@Service
public class CreditCardServiceImpl implements CreditCardService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreditCardServiceImpl.class);

    CreditCardRepository creditCardRepository;
    CardValidationUtil cardValidationUtil;

    public CreditCardServiceImpl(CreditCardRepository creditCardRepository, CardValidationUtil cardValidationUtil){
        this.creditCardRepository = creditCardRepository;
        this.cardValidationUtil = cardValidationUtil;
    }

    /**
     * This method saves the credit card
     * data into the database after the request validation
     * @param creditCardRequestObj
     * @return message of success/failure
     * @throws InvalidDataException
     */
    @Override
    public String registerNewCreditCard(CreditCardBean creditCardRequestObj) throws InvalidDataException{

        if(creditCardRequestObj.getCardNumber().length() > ApplicationConstant.ALLOWED_CHARACTERS_FOR_CREDIT_CARD)
            return ApplicationConstant.ERROR_CARD_NUMBER_MAX_LENGTH;

        if(cardValidationUtil.isValidCreditCardNumber(creditCardRequestObj.getCardNumber())) {
            CreditCardEntity creditCardEntity = new CreditCardEntity();
            BeanUtils.copyProperties(creditCardRequestObj, creditCardEntity);
            creditCardEntity.setBalance(ApplicationConstant.DEFAULT_CREDIT_CARD_BALANCE);
            creditCardRepository.save(creditCardEntity);
            LOGGER.trace("Card saved successfully in memory database..");
            return ApplicationConstant.CREDIT_CARD_SAVED_SUCCESSFULLY;

        }else{
            LOGGER.error(ApplicationConstant.INVALID_CARD_NUMBER);
            throw new InvalidDataException(ApplicationConstant.INVALID_CARD_NUMBER);
        }
    }

    /**
     * This method is to fetch all the credit cards
     * present in the system
     * @param : none
     * @return : list of CreditCardBean
     */
    @Override
    public List<CreditCardBean> retrieveCreditCard() {
        Iterable<CreditCardEntity> creditCardEntity = creditCardRepository.findAll();

        List<CreditCardBean> creditCardBeanList = new ArrayList<>();

        if(null != creditCardEntity){
            creditCardEntity.forEach(creditCardEntityObj -> {
                CreditCardBean creditCardBean = new CreditCardBean();
                BeanUtils.copyProperties(creditCardEntityObj, creditCardBean);
                creditCardBeanList.add(creditCardBean);
            });
        }
        LOGGER.trace(ApplicationConstant.CREDIT_CARD_DETAILS_RETRIEVED_SUCCESSFULLY);
        return creditCardBeanList;
    }
}
