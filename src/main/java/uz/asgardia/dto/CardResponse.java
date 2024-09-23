package uz.asgardia.dto;

import jakarta.validation.constraints.NotNull;
import uz.asgardia.enums.CardStatus;
import uz.asgardia.enums.Currency;

public class CardResponse {
    @NotNull
    private String cardId;
    @NotNull
    private Long userId;
    @NotNull
    private CardStatus status;
    @NotNull
    private Long balance;
    @NotNull
    private Currency currency;

    public CardResponse(String cardId, Long userId, CardStatus status, Long balance, Currency currency) {
        this.cardId = cardId;
        this.userId = userId;
        this.status = status;
        this.balance = balance;
        this.currency = currency;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public CardStatus getStatus() {
        return status;
    }

    public void setStatus(CardStatus status) {
        this.status = status;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
