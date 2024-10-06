package uz.asgardia.card.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import uz.asgardia.card.enums.Currency;
import uz.asgardia.card.enums.Purpose;

public class WithdrawResponse {

    @NotNull
    @JsonProperty(namespace = "transaction_id")
    private String transactionId;

    @NotNull
    @JsonProperty(namespace = "external_id")
    private String externalId;

    @NotNull
    @JsonProperty(namespace = "card_id")
    private String cardId;

    @NotNull
    @JsonProperty(namespace = "after_balance")
    private long afterBalance;

    @NotNull
    @JsonProperty(namespace = "amount")
    private long amount;

    @NotNull
    @JsonProperty(namespace = "currency")
    private Currency currency = Currency.UZS;

    @NotNull
    @JsonProperty(namespace = "purpose")
    private Purpose purpose;

    @JsonProperty(namespace = "exchange_rate")
    private long exchangeRate;


    public @NotNull String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(@NotNull String transactionId) {
        this.transactionId = transactionId;
    }

    public @NotNull String getExternalId() {
        return externalId;
    }

    public void setExternalId(@NotNull String externalId) {
        this.externalId = externalId;
    }

    public @NotNull String getCardId() {
        return cardId;
    }

    public void setCardId(@NotNull String cardId) {
        this.cardId = cardId;
    }

    @NotNull
    public long getAfterBalance() {
        return afterBalance;
    }

    public void setAfterBalance(@NotNull long afterBalance) {
        this.afterBalance = afterBalance;
    }

    @NotNull
    public long getAmount() {
        return amount;
    }

    public void setAmount(@NotNull long amount) {
        this.amount = amount;
    }

    public @NotNull Currency getCurrency() {
        return currency;
    }

    public void setCurrency(@NotNull Currency currency) {
        this.currency = currency;
    }

    public @NotNull Purpose getPurpose() {
        return purpose;
    }

    public void setPurpose(@NotNull Purpose purpose) {
        this.purpose = purpose;
    }

    public long getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(long exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
}
