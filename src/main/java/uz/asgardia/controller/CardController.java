package uz.asgardia.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.asgardia.entity.Card;
import uz.asgardia.service.CardService;

@RestController
@RequestMapping("/api/v1/cards")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping(value = "/add")
    public HttpEntity<?> addCard(@Valid Card card){
        Card addedUser = cardService.addCard(card);
        return ResponseEntity.status(201).body(addedUser);
    }
}
