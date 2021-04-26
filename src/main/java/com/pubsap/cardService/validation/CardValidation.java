package com.pubsap.cardService.validation;

import org.springframework.stereotype.Component;

@Component
public class CardValidation {

    /**
     * Does LUHN Check on Card Number
     * @param cardNumber
     * @return
     */
    public boolean validateCardNumber(String cardNumber) {
        if(cardNumber == null || cardNumber.length() < 1 || cardNumber.length() > 19 || !cardNumber.matches("[0-9]+"))
            return false;
        int cardLength = cardNumber.length();
        int sumOfDigits = 0;
        boolean isTwoDigit = false;
        for (int i = cardLength - 1; i >= 0; i--) {
            int d = cardNumber.charAt(i) - '0';
            if (isTwoDigit == true)
                d = d * 2;
                sumOfDigits += d / 10;
                sumOfDigits += d % 10;
                isTwoDigit = !isTwoDigit;
        }
        return (sumOfDigits % 10 == 0);
    }

    /**
     * Does check on card Limit
     * @param cardLimit
     * @return
     */
    public boolean validateCardLimit(Double cardLimit) {
        if(cardLimit == null || cardLimit < 0) {
            return false;
        } else {
            return true;
        }
    }
}

