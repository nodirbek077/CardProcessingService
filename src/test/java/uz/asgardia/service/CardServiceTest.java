package uz.asgardia.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import uz.asgardia.card.model.Card;
import uz.asgardia.card.dto.CardRequest;
import uz.asgardia.card.enums.CardStatus;
import uz.asgardia.card.enums.Currency;
import uz.asgardia.card.repository.CardRepository;
import uz.asgardia.card.service.CardService;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
public class CardServiceTest {
    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private CardService cardService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCard(){
        CardRequest request = new CardRequest();
        request.setUserId(12345L);
        request.setStatus(CardStatus.ACTIVE);
        request.setInitialAmount(1000L);
        request.setCurrency(Currency.UZS);

        when(cardRepository.save(any(Card.class))).thenReturn(new Card());

        Card resultCard = cardService.createCard(request, "some-idempotency-key");

        assertNotNull(resultCard);
        assertEquals(CardStatus.ACTIVE, resultCard.getStatus());
        verify(cardRepository, times(1)).save(any(Card.class));
    }
}
