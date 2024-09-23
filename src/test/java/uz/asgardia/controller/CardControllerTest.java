//package uz.asgardia.controller;
//
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import uz.asgardia.entity.Card;
//import uz.asgardia.entity.request.CardRequest;
//import uz.asgardia.enums.CardStatus;
//import uz.asgardia.enums.Currency;
//import uz.asgardia.service.CardService;
//
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//@RunWith(SpringRunner.class)
//@WebMvcTest(CardController.class)
//public class CardControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private CardService cardService;
//
//    @Test
//    public void testCreateCardSuccess() throws Exception {
//        CardRequest request = new CardRequest();
//        request.setUserId(12345L);
//        request.setStatus(CardStatus.ACTIVE);
//
//        Card card = new Card();
//        card.setCardId("b0396238-363d-4bb2-acdf-e117312078df");
//        card.setUserId(12345L);
//        card.setStatus(CardStatus.ACTIVE);
//        card.setBalance(0L);
//        card.setCurrency(Currency.UZS);
//
//        when(cardService.createCard(any(CardRequest.class), anyString()))
//                .thenReturn(card);
//
//        mockMvc.perform(post("/api/v1/cards/add")
//                        .header("Idempotency-Key", "123e4567-e89b-12d3-a456-426614174000")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"user_id\": 12345, \"status\": \"ACTIVE\"}"))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.card_id").value("b0396238-363d-4bb2-acdf-e117312078df"));
//
//    }
//}
