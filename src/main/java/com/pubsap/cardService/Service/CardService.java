package com.pubsap.cardService.Service;

import com.pubsap.cardService.Exeption.NoRecordFoundException;
import com.pubsap.cardService.Exeption.ValidationFailureException;
import com.pubsap.cardService.Model.Creditcard;
import com.pubsap.cardService.Repository.CardRepository;
import com.pubsap.cardService.validation.CardValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    private  CardValidation cardValidation = new CardValidation();

    public void setCardRepository(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    /**
     * Fetches List of all availabl ecards and throw  404 if list is empty
     * @return LIst of Credit Cards
     */
    public List<Creditcard> findAll() {
        List<Creditcard> listOfCards = cardRepository.findAll();
        if(listOfCards == null || listOfCards.isEmpty()){
            throw new NoRecordFoundException("No Cards Available" );
        }
        return listOfCards;
    }

    /**
     * Adds a valid card
     * @param creditCard
     * @throws Throwable
     */
    public void save(Creditcard creditCard)  {
        if(!cardValidation.validateCardNumber(creditCard.getCardnumber())){
            throw new ValidationFailureException("Incorrect Card Number");
        }
        if(!cardValidation.validateCardLimit(creditCard.getCardlimit())){
            throw new ValidationFailureException("Invalid Card Limit");
        }
        cardRepository.save(creditCard);
    }
}
