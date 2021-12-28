package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.netology.model.Card;
import ru.netology.model.request.TransferRQ;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.*;
import static ru.netology.TestData.*;

class TransferMoneyRepositoryImplTest {

    private TransferMoneyRepository repository;

    private final Map<String, Card> testCards = new ConcurrentHashMap<>();

    @BeforeEach
    void setUp() {
        repository = new TransferMoneyRepositoryImpl();

        testCards.put(CARD_NUMBER_1, CARD_1);
        testCards.put(CARD_NUMBER_2, CARD_2);
        testCards.put(CARD_NUMBER_3, CARD_3);
    }

    @ParameterizedTest
    @ValueSource(strings = {CARD_NUMBER_1, CARD_NUMBER_2, CARD_NUMBER_3})
    void getCard(String cardNumber) {
        assertEquals(testCards.get(cardNumber), repository.getCard(cardNumber));
    }

    @Test
    void incrementAndGetOperationId() {
        int forTestOperationId = 1;
        assertEquals(repository.incrementAndGetOperationId(), forTestOperationId);
    }

    @Test
    public void putAndRemoveTransfers() {
        TransferRQ beforePut = repository.removeTransfer(OPERATION_ID);
        assertNull(beforePut);
        repository.putTransfers(OPERATION_ID, TRANSFER_RQ_4_5);
        TransferRQ afterPut = repository.removeTransfer(OPERATION_ID);
        assertEquals(afterPut, TRANSFER_RQ_4_5);
    }

    @Test
    public void putAndRemoveCodes() {
        String beforePut = repository.removeCode(OPERATION_ID);
        assertNull(beforePut);
        repository.putCodes(OPERATION_ID, CODE);
        String afterPut = repository.removeCode(OPERATION_ID);
        assertEquals(afterPut, CODE);
    }
}