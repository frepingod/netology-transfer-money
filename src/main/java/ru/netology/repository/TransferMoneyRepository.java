package ru.netology.repository;

import ru.netology.model.Card;
import ru.netology.model.ConfirmOperation;

public interface TransferMoneyRepository {

    Card getCard(String cardNumber);

    int getAndIncrementTransferOperationId();

    ConfirmOperation getConfirmOperation(String operationId);
}