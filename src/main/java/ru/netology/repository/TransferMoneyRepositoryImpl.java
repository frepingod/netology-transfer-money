package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.model.Amount;
import ru.netology.model.Card;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TransferMoneyRepositoryImpl implements TransferMoneyRepository {

    private final Map<String, Card> cards = new ConcurrentHashMap<>();

    {
        final String cardNumber1 = "1111111111111111";
        final String cardNumber2 = "2222222222222222";
        final String cardNumber3 = "3333333333333333";

        cards.put(cardNumber1, new Card(cardNumber1, "08/26", "123", new Amount(1000_00, "RUR")));
        cards.put(cardNumber2, new Card(cardNumber2, "08/27", "124", new Amount(1000_00, "RUR")));
        cards.put(cardNumber3, new Card(cardNumber3, "08/28", "125", new Amount(1000_00, "RUR")));
    }

    @Override
    public Card getCard(String cardNumber) {
        return cards.get(cardNumber);
    }
}