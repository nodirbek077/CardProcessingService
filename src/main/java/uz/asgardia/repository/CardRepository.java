package uz.asgardia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.asgardia.model.Card;
import uz.asgardia.enums.CardStatus;

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
