package uz.asgardia.card.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;
import uz.asgardia.card.dto.WithdrawRequest;
import uz.asgardia.card.exception.CardIdNotFoundException;
import uz.asgardia.card.exception.NoEnoughMoneyException;
import uz.asgardia.card.model.Card;
import uz.asgardia.card.dto.CardRequest;
import uz.asgardia.card.enums.CardStatus;
import uz.asgardia.card.enums.Currency;
import uz.asgardia.card.exception.CardLimitExceededException;
import uz.asgardia.card.repository.CardRepository;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CardService {
    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Card createCard(CardRequest request, String idempotencyKey){
        // check if the card with the given idempotency key already exists
        Optional<Card> existingCard = cardRepository.findByIdempotencyKey(idempotencyKey);
        if (existingCard.isPresent())
            return existingCard.get();

        // check if user already has 3 non-closed cards (ACTIVE or BLOCKED)
        List<CardStatus> nonClosedStatusList = Arrays.asList(CardStatus.ACTIVE, CardStatus.BLOCKED);
        List<Card> nonClosedCards = cardRepository.findByUserIdAndStatusIn(request.getUserId(), nonClosedStatusList);

        //check if user already has more than none closed cards
        if (nonClosedCards.size() >= 3)
            throw new CardLimitExceededException("User already owns the maximum number of cards");

        //create and save the new card data to the database
        Card card = new Card();
        card.setUserId(request.getUserId());
        card.setStatus(request.getStatus() != null ? request.getStatus() : CardStatus.ACTIVE);
        card.setBalance(request.getInitialAmount() != null ? request.getInitialAmount() : 0L);
        card.setCurrency(request.getCurrency() != null ? request.getCurrency() : Currency.UZS);
        card.setCardId(UUID.randomUUID().toString());
        card.setIdempotencyKey(idempotencyKey);

        return cardRepository.save(card);
    }

    public Card getCardById(String cardId){
        return cardRepository.findByCardId(cardId);
    }

    public void blockCard(String cardId, String ifMatch) {

        //check is the cardId and active status exist or not in the database
        Card existingCard = cardRepository.findByCardIdAndStatus(cardId, CardStatus.ACTIVE);

        if (existingCard == null)
            throw new CardIdNotFoundException("If the card with the specified id is not found.");

        String generatedETag = existingCard.getCardId() + existingCard.getStatus() + existingCard.getBalance() + existingCard.getCurrency();
        String currentETag = DigestUtils.md5DigestAsHex(generatedETag.getBytes(StandardCharsets.UTF_8));

        if (!ifMatch.equals("\"" + currentETag + "\""))
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "ETag mismatch");

        existingCard.setStatus(CardStatus.BLOCKED);
        cardRepository.save(existingCard);
    }

    public void unblockCard(String cardId, String ifMatch) {

        //check is the cardId and active status exist or not in the database
        Card existingCard = cardRepository.findByCardIdAndStatus(cardId, CardStatus.BLOCKED);

        if (existingCard == null)
            throw new CardIdNotFoundException("If the card with the specified id is not found.");

        String generatedETag = existingCard.getCardId() + existingCard.getStatus() + existingCard.getBalance() + existingCard.getCurrency();
        String currentETag = DigestUtils.md5DigestAsHex(generatedETag.getBytes(StandardCharsets.UTF_8));

        if (!ifMatch.equals("\"" + currentETag + "\""))
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "ETag mismatch");

        existingCard.setStatus(CardStatus.ACTIVE);
        cardRepository.save(existingCard);
    }

    public Card withdrawFund(WithdrawRequest request, String cardId){

        Card existingCard = cardRepository.findByCardIdAndStatus(cardId, CardStatus.ACTIVE);

        if (existingCard == null)
            throw new CardIdNotFoundException("The card with the specified id is not found.");

        if (existingCard.getBalance() == null)
            throw new NoEnoughMoneyException("Not found enough fund for withdrawing");

        //here calculating and checking the different types of currency
/*
        // check if user already has 3 non-closed cards (ACTIVE or BLOCKED)
        List<CardStatus> nonClosedStatusList = Arrays.asList(CardStatus.ACTIVE, CardStatus.BLOCKED);
        List<Card> nonClosedCards = cardRepository.findByUserIdAndStatusIn(request.getUserId(), nonClosedStatusList);

        //check if user already has more than none closed cards
        if (nonClosedCards.size() >= 3)
            throw new CardLimitExceededException("User already owns the maximum number of cards");

        //create and save the new card data to the database
        Card card = new Card();
        card.setUserId(request.getUserId());
        card.setStatus(request.getStatus() != null ? request.getStatus() : CardStatus.ACTIVE);
        card.setBalance(request.getInitialAmount() != null ? request.getInitialAmount() : 0L);
        card.setCurrency(request.getCurrency() != null ? request.getCurrency() : Currency.UZS);
        card.setCardId(UUID.randomUUID().toString());
        card.setIdempotencyKey(idempotencyKey);

        return cardRepository.save(card);*/
        return null;
    }
}
