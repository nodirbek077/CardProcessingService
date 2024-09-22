package uz.asgardia.entity.response;

import uz.asgardia.enums.CardStatus;
import uz.asgardia.enums.CurrencyType;

import java.util.UUID;

public class Response {
    private UUID cardId;
    private long userId;
    private CardStatus status;
    private long balance;
    private CurrencyType currency;

    public UUID getCardId() {
        return cardId;
    }

    public void setCardId(UUID cardId) {
        this.cardId = cardId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public CardStatus getStatus() {
        return status;
    }

    public void setStatus(CardStatus status) {
        this.status = status;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public CurrencyType getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyType currency) {
        this.currency = currency;
    }
}
