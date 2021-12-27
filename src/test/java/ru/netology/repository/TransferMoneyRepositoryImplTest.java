package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.netology.model.Amount;
import ru.netology.model.Card;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.*;

class TransferMoneyRepositoryImplTest {

    private final TransferMoneyRepository repository = new TransferMoneyRepositoryImpl();

    private final Map<String, Card> testCards = new ConcurrentHashMap<>();

    private final String testCardNumber1 = "1111111111111111";
    private final String testCardNumber2 = "2222222222222222";
    private final String testCardNumber3 = "3333333333333333";

    private final Card testCard1 = new Card(testCardNumber1, "08/26", "123", new Amount(1000_00, "RUR"));
    private final Card testCard2 = new Card(testCardNumber2, "08/27", "124", new Amount(1000_00, "RUR"));
    private final Card testCard3 = new Card(testCardNumber3, "08/28", "125", new Amount(1000_00, "RUR"));

    @BeforeEach
    void setUp() {
        testCards.put(testCardNumber1, testCard1);
        testCards.put(testCardNumber2, testCard2);
        testCards.put(testCardNumber3, testCard3);
    }

    @ParameterizedTest
    @ValueSource(strings = {testCardNumber1, testCardNumber2, testCardNumber3})
    void getCard(String cardNumber) {
        assertEquals(testCards.get(cardNumber), repository.getCard(cardNumber));
    }
}