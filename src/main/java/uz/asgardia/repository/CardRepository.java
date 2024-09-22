package uz.asgardia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.asgardia.entity.Card;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {
    List<Card> findAll();
}
