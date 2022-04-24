package com.publicis.sapient.creditcardsystem.service.util;

import org.springframework.stereotype.Component;

/**
 * Responsible for card validation as per Luhn rule
 */
@Component
public class CardValidationUtil {

    /**
     * Responsible for validation card number against Luhn algo
     * @param cardNumber : numeric card no send in request
     * @return : boolean
     */
    public static boolean isValidCreditCardNumber(String cardNumber) {

        int sum = 0;
        boolean alternate = false;
        for (int i = cardNumber.length() - 1; i >= 0; i--)
        {
            int n = Integer.parseInt(cardNumber.substring(i, i + 1));
            if (alternate)
            {
                n *= 2;
                if (n > 9)
                {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }
}
