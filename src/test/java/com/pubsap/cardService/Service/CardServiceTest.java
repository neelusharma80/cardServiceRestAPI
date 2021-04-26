package com.pubsap.cardService.Service;

import com.pubsap.cardService.Exeption.NoRecordFoundException;
import com.pubsap.cardService.Exeption.ValidationFailureException;
import com.pubsap.cardService.Model.Creditcard;
import com.pubsap.cardService.Repository.CardRepository;
import com.pubsap.cardService.validation.CardValidation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static junit.framework.TestCase.*;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
public class CardServiceTest {

    @Mock
    CardRepository creditcardRepository;

    @Mock
    CardValidation cardValidation;

    @InjectMocks
    CardService cardService;

    List<Creditcard> creditCardList = new ArrayList<>();
    List<Creditcard> creditCardLisEmpty = new ArrayList<>();
    Creditcard creditcardA;
    Creditcard creditcardB;
    Creditcard creditcardC;
    Creditcard creditcardD;


    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        creditcardA = new Creditcard("41111111111111111","validCardA", 500.00);
        creditcardB = new Creditcard("20202020","validCardB", 500.00);
        creditcardC = new Creditcard("444BB56","validCardC", -900.00);
        creditcardD = new Creditcard("411111111111111111111111111111111111111111","validCardA", 500.00);
        creditCardList = new ArrayList<>();
        creditCardList.add(creditcardA);
        creditCardList.add(creditcardB);
        creditCardList.add(creditcardC);
    }

    @Test
    public void testValidfindAll(){
        Mockito.when(creditcardRepository.findAll()).thenReturn(creditCardList);
        List<Creditcard> creditCard = cardService.findAll();
        assertFalse(creditCard.isEmpty());
        assertEquals(3,creditCardList.size());
    }



    @Test
    void testInValidfindAll() {
        Mockito.when(creditcardRepository.findAll()).thenReturn(creditCardLisEmpty);
        NoRecordFoundException thrown = assertThrows(
                NoRecordFoundException.class,
                () -> cardService.findAll(),
                "No Cards Available"
        );
    }

    @Test
    public void testValidSave(){
        Mockito.when(cardValidation.validateCardNumber("41111111111111111")).thenReturn(true);
        Mockito.when(cardValidation.validateCardLimit(500.00)).thenReturn(true);
        cardService.save(creditcardA);
        Mockito.verify(creditcardRepository, Mockito.times(1)).save(eq(creditcardA));
    }

    @Test
    void testInValidCardNumSave() {
        ValidationFailureException thrown = assertThrows(
                ValidationFailureException.class,
                () -> cardService.save(creditcardB),
                "Incorrect Card Number"
        );
    }

    @Test
    void testInValidCardLimitSave() {
        ValidationFailureException thrown = assertThrows(
                ValidationFailureException.class,
                () -> cardService.save(creditcardB),
                "Invalid Card Limit"
        );
    }

    @Test
    void testCardOnlyNumericSave() {
        ValidationFailureException thrown = assertThrows(
                ValidationFailureException.class,
                () -> cardService.save(creditcardD),
                "Invalid Card Number"
        );
    }
}
