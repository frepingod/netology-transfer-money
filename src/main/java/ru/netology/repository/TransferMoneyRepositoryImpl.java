package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.model.Amount;
import ru.netology.model.Card;
import ru.netology.model.ConfirmOperation;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class TransferMoneyRepositoryImpl implements TransferMoneyRepository {

    private final Map<String, Card> cards = new ConcurrentHashMap<>();
    private final AtomicInteger transferOperationId = new AtomicInteger();

    private final Map<String, ConfirmOperation> confirms = new ConcurrentHashMap<>();

    {
        final String cardNumber1 = "1111 1111 1111 1111";
        final String cardNumber2 = "2222 2222 2222 2222";
        final String cardNumber3 = "3333 3333 3333 3333";

        cards.put(cardNumber1, new Card(cardNumber1, "08/26", "123", new Amount(1000, "RUB")));
        cards.put(cardNumber2, new Card(cardNumber2, "08/27", "124", new Amount(1000, "RUB")));
        cards.put(cardNumber3, new Card(cardNumber3, "08/28", "125", new Amount(1000, "RUB")));

        //----------//----------//----------//----------//----------//----------//----------//----------//

        final String operationId1 = "1";
        final String operationId2 = "2";
        final String operationId3 = "3";

        confirms.put(operationId1, new ConfirmOperation(operationId1, "code1", "description1"));
        confirms.put(operationId2, new ConfirmOperation(operationId2, "code2", "description2"));
        confirms.put(operationId3, new ConfirmOperation(operationId3, "code3", "description3"));
    }

    @Override
    public Card getCard(String cardNumber) {
        return cards.get(cardNumber);
    }

    @Override
    public int incrementAndGetTransferOperationId() {
        return transferOperationId.incrementAndGet();
    }

    @Override
    public ConfirmOperation getConfirmOperation(String operationId) {
        return confirms.get(operationId);
    }
}