package ru.netology.repository;

import ru.netology.model.Card;
import ru.netology.model.request.TransferRQ;

public interface TransferMoneyRepository {

    Card getCard(String cardNumber);

    int incrementAndGetOperationId();

    void putTransfers(String id, TransferRQ transferRQ);

    void putCodes(String id, String code);

    TransferRQ removeTransfer(String id);

    String removeCode(String id);
}