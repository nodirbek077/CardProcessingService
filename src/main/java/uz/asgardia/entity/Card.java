package uz.asgardia.entity;

import jakarta.persistence.*;
import uz.asgardia.enums.CardStatus;
import uz.asgardia.enums.CurrencyType;

import java.util.UUID;

@Entity
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Enumerated
    @Column(name = "status")
    private CardStatus status = CardStatus.ACTIVE;
    @Column(name = "initial_amount")
    private long initialAmount;
    @Enumerated
    @Column(name = "currency")
    private CurrencyType currency = CurrencyType.UZS;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
