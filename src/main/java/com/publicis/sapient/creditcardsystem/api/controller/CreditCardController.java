package com.publicis.sapient.creditcardsystem.api.controller;

import com.publicis.sapient.creditcardsystem.service.ApplicationConstant;
import com.publicis.sapient.creditcardsystem.service.bean.CreditCardBean;
import com.sun.media.sound.InvalidDataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.publicis.sapient.creditcardsystem.service.CreditCardService;

/**
 * This controller provides
 * two APIs for the credit card system
 * Both API's takes and produce data in JSON format
 */
@CrossOrigin
@RestController
@Validated
public class CreditCardController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreditCardController.class);

    @Autowired
    CreditCardService creditCardService;

    /**
     * This method is to add valid credit card to the
     * system via service layer
     * It has field validation for cardNumber
     * length and type
     * @param creditCardRequestObj
     * @return proper message for submission/failure
     */
    @PostMapping(value = "/credit-card", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> registerCreditCard(@RequestBody CreditCardBean creditCardRequestObj){

        try{
            return new ResponseEntity(creditCardService.registerCreditCard(creditCardRequestObj), HttpStatus.OK);
        }catch (NumberFormatException ex){
            LOGGER.error(ApplicationConstant.CARD_NUMBER_NOT_NUMERIC, creditCardRequestObj.getCardNumber());
            return new ResponseEntity<>(ApplicationConstant.CARD_NUMBER_NOT_NUMERIC, HttpStatus.BAD_REQUEST);

        }catch(InvalidDataException ex){
            LOGGER.error(ApplicationConstant.INVALID_CARD_NUMBER, creditCardRequestObj.getCardNumber());
            return new ResponseEntity<>(ApplicationConstant.INVALID_CARD_NUMBER, HttpStatus.BAD_REQUEST);

        }catch (Exception ex){
            LOGGER.error(ApplicationConstant.ERROR_OCCURRED_FOR_ADD_CREDIT_CARD, ex.getMessage());
            return new ResponseEntity<>(ApplicationConstant.ERROR_OCCURRED_FOR_ADD_CREDIT_CARD, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This method is to getAllCreditCards in system
     * no argument
     * @return list of creditCards
     */
    @GetMapping(value="/credit-card/all-cards", produces = "application/json")
    public ResponseEntity<?> retrieveCreditCard(){
        try{
            return new ResponseEntity(creditCardService.retrieveCreditCard(), HttpStatus.OK);
        }catch (Exception ex){
            LOGGER.error(ApplicationConstant.ERROR_OCCURRED_FOR_GET_ALL_CREDIT_CARD, ex.getMessage());
            return new ResponseEntity<>(ApplicationConstant.ERROR_OCCURRED_FOR_GET_ALL_CREDIT_CARD, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
