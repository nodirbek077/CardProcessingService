package uz.asgardia.card.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.asgardia.card.model.Card;
import uz.asgardia.card.enums.CardStatus;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<Card,Long> {
    List<Card> findByUserIdAndStatusIn(Long userId, List<CardStatus> statuses);
    Optional<Card> findByIdempotencyKey(String idempotencyKey);
    Card findByCardIdAndStatus(String cardId, CardStatus status);
    Card findByCardId(String cardId);

//    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Card c WHERE c.status = :status")
//    Boolean findCardStatus(@Param("status") CardStatus status);

}
