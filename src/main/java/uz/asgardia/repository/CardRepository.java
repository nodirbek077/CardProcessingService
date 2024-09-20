package uz.asgardia.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uz.asgardia.entity.Card;

@Repository
public interface CardRepository extends CrudRepository<Card, String> {

}
