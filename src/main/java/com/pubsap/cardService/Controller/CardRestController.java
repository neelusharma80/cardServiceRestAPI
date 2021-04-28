package com.pubsap.cardService.Controller;

import java.util.List;

import com.pubsap.cardService.Service.CardService;
import com.pubsap.cardService.Model.Creditcard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CardRestController {

    @Autowired
    private CardService cardService;

    public void setCardService(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/api/v1/listCards")
    public ResponseEntity<List<Creditcard>> listAllCards() {
        List<Creditcard> creditCards = cardService.findAll();
        return new ResponseEntity<>(creditCards, HttpStatus.OK);
    }

    @PostMapping("/api/v1/addCard")
    public ResponseEntity<Creditcard> addCard(@RequestBody Creditcard creditCard) {
        cardService.save(creditCard);
        return new ResponseEntity<>(creditCard, HttpStatus.CREATED);
    }
}

