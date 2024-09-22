package uz.asgardia.entity.request;

import uz.asgardia.enums.CardStatus;

public class Request {
    private Integer userId;
    private CardStatus status = CardStatus.ACTIVE;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public CardStatus getStatus() {
        return status;
    }

    public void setStatus(CardStatus status) {
        this.status = status;
    }
}
