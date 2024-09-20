package uz.asgardia.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import uz.asgardia.entity.Card;
import uz.asgardia.entity.User;
import uz.asgardia.repository.CardRepository;

@Service
@Transactional
public class CardService {
    private final CardRepository cardRepository;
    private UserService userService;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Card addCard(Card card){
        Card newCard = new Card();
        newCard.setIdempotencyKey("1");
        newCard.setInitialAmount(10);

        User user = new User();
        User userById = userService.getUserById(user);
        if (userById != null)
            newCard.setUser(user);

        return cardRepository.save(card);
    }
}
