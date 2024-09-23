package uz.asgardia.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import uz.asgardia.model.Card;
import uz.asgardia.dto.CardRequest;
import uz.asgardia.dto.CardResponse;
import uz.asgardia.service.CardService;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/v1/cards")
public class CardController {
    private final CardService cardService;
    final Logger LOGGER = LoggerFactory.getLogger(CardController.class);
    public CardController( CardService cardService) {
        this.cardService = cardService;
    }
    @PostMapping
    public ResponseEntity<CardResponse> createCard(@RequestHeader("Idempotency-Key") String idempotencyKey, @RequestBody @Valid CardRequest request){
        Card card = cardService.createCard(request, idempotencyKey);
        return new ResponseEntity<>(getCardResponse(card), HttpStatus.CREATED);
    }

    @GetMapping("/{cardId}")
    public ResponseEntity<CardResponse> getCardByCardId(@PathVariable String cardId){
        Card currentCard = cardService.getCardById(cardId);

        String eTagSource = currentCard.getCardId() + currentCard.getStatus() + currentCard.getBalance() + currentCard.getCurrency();

        // Generate an MD5 hash as the ETag
        String eTag = DigestUtils.md5DigestAsHex(eTagSource.getBytes(StandardCharsets.UTF_8));

        return ResponseEntity
                .ok()
                .header(HttpHeaders.ETAG, eTag)
                .body(getCardResponse(currentCard));
    }

    @PostMapping("/{cardId}/block")
    public ResponseEntity<Void> blockCard(@PathVariable String cardId, @RequestHeader("If-Match") String ifMatch){
        cardService.blockCard(cardId, ifMatch);
        return ResponseEntity.noContent().build();
    }

    private CardResponse getCardResponse(Card card){
        return new CardResponse(
                card.getCardId(),
                card.getUserId(),
                card.getStatus(),
                card.getBalance(),
                card.getCurrency()
        );
    }
}
