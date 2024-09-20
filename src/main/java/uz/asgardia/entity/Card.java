package uz.asgardia.entity;

import jakarta.persistence.*;
import uz.asgardia.enums.CardStatus;
import uz.asgardia.enums.CurrencyType;


@Entity
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "idempotency_key")
    private String idempotencyKey;

    @OneToMany
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated
    @Column(name = "status")
    private CardStatus status = CardStatus.ACTIVE;

    @Column(name = "initial_amount")
    private long initialAmount;

    @Enumerated
    @Column(name = "currency")
    private CurrencyType currency = CurrencyType.UZS;

    public Card() {
    }

    public Card(String idempotencyKey, User user, CardStatus status, long initialAmount, CurrencyType currency) {
        this.idempotencyKey = idempotencyKey;
        this.user = user;
        this.status = status;
        this.initialAmount = initialAmount;
        this.currency = currency;
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public void setIdempotencyKey(String idempotencyKey) {
        this.idempotencyKey = idempotencyKey;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CardStatus getStatus() {
        return status;
    }

    public void setStatus(CardStatus status) {
        this.status = status;
    }

    public long getInitialAmount() {
        return initialAmount;
    }

    public void setInitialAmount(long initialAmount) {
        this.initialAmount = initialAmount;
    }

    public CurrencyType getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyType currency) {
        this.currency = currency;
    }
}
