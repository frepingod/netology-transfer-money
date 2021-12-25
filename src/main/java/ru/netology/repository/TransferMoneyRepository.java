package ru.netology.repository;

import ru.netology.model.Card;
import ru.netology.model.ConfirmOperation;

public interface TransferMoneyRepository {

    Card getCard(String cardNumber);

    int incrementAndGetTransferOperationId();

    ConfirmOperation getConfirmOperation(String operationId);
}