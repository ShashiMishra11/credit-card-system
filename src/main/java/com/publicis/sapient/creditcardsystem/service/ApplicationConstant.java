package com.publicis.sapient.creditcardsystem.service;

public interface ApplicationConstant {

    String CREDIT_CARD_SAVED_SUCCESSFULLY = "Credit card details saved successfully";
    String INVALID_CARD_NUMBER = "Card number is not compatible with Luhn 10 algorithm ";
    float DEFAULT_CREDIT_CARD_BALANCE = 0;
    String INTERNAL_SERVER_ERROR_CODE = "Error occurred will processing the request";
    String CARD_NUMBER_NOT_NUMERIC = "Entered card number is not numeric ";
    int ALLOWED_CHARACTERS_FOR_CREDIT_CARD = 19;
    String ERROR_CARD_NUMBER_MAX_LENGTH = "Card Number can only contain upto 19 characters";
    String ERROR_OCCURRED_FOR_ADD_CREDIT_CARD="Error occurred while saving the credit card : {}";
    String ERROR_OCCURRED_FOR_GET_ALL_CREDIT_CARD="Error occurred while saving the credit card : {}";
    String CREDIT_CARD_DETAILS_RETRIEVED_SUCCESSFULLY="Credit card details retrieved successfully..";
}
