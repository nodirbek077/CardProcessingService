package uz.asgardia.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import uz.asgardia.enums.Currency;
import uz.asgardia.enums.Purpose;

public class WithdrawRequest {

    @NotNull
    @JsonProperty(namespace = "external_id")
    private String externalId;

    @NotNull
    @JsonProperty(namespace = "amount")
    private long amount;//amount of money in tiyin

    @JsonProperty(namespace = "currency")
    private Currency currency = Currency.UZS;

    @NotNull
    @JsonProperty(namespace = "purpose")
    private Purpose purpose;

    public WithdrawRequest(String externalId, long amount, Currency currency, Purpose purpose) {
        this.externalId = externalId;
        this.amount = amount;
        this.currency = currency;
        this.purpose = purpose;
    }

    public @NotNull String getExternalId() {
        return externalId;
    }

    public void setExternalId(@NotNull String externalId) {
        this.externalId = externalId;
    }

    @NotNull
    public long getAmount() {
        return amount;
    }

    public void setAmount(@NotNull long amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public @NotNull Purpose getPurpose() {
        return purpose;
    }

    public void setPurpose(@NotNull Purpose purpose) {
        this.purpose = purpose;
    }
}
