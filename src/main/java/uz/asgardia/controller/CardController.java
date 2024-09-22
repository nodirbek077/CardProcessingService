package uz.asgardia.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.simple.SimpleLoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.asgardia.entity.Card;
import uz.asgardia.entity.User;
import uz.asgardia.entity.request.Request;
import uz.asgardia.entity.response.Response;
import uz.asgardia.repository.CardRepository;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cards")
public class CardController {
    private final CardRepository cardRepository;
    public CardController(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    final Logger LOGGER = LoggerFactory.getLogger(CardController.class);
    @PostMapping("/add")
    public ResponseEntity<?> addCard(@RequestBody Card card){

        Card newCard = new Card();
        newCard.setStatus(card.getStatus());
        newCard.setCurrency(card.getCurrency());
        newCard.setInitialAmount(card.getInitialAmount());

        User user = new User();
        newCard.setUser(user);

        Request request = new Request();
        request.setStatus(newCard.getStatus());
        request.setUserId(user.getId());

        Response response = new Response();
        response.setBalance(newCard.getInitialAmount());
        response.setCurrency(newCard.getCurrency());
        response.setStatus(newCard.getStatus());
        response.setUserId(newCard.getUser().getId());
        response.setCardId(newCard.getId());

        cardRepository.save(newCard);
        LOGGER.debug("Request is {} \n Response is {}", request, response);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

//    @GetMapping
//    public ResponseEntity<?> testList(){
//        List<Card> allCards = cardRepository.findAll();
//
//        if (allCards.isEmpty())
//            return ResponseEntity.noContent().build();
//
//        return new ResponseEntity<>(allCards, HttpStatus.OK);
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteTest(@PathVariable Integer id){
//        if (cardRepository.findById(id).isPresent()) {
//            deleteTaskById(id);
//            return ResponseEntity.ok("Test deleted successfully");
//        }else
//            return ResponseEntity.status(404).body("Test not found with this ID");
//    }

//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateTest(@PathVariable Integer id, @RequestBody Card updatingCard){
//        Optional<Card> testOptional = cardRepository.findById(id);
//        if (testOptional.isPresent()) {
//            Card oldCard = testOptional.get();
//            oldCard.setName(updatingCard.getName());
//
//            cardRepository.save(oldCard);
//            return ResponseEntity.ok("Test updated successfully.");
//        }else
//            return ResponseEntity.status(404).body("Test not found with this ID");
//    }



    private void deleteTaskById(Integer id){
        cardRepository.deleteById(id);
    }
}
