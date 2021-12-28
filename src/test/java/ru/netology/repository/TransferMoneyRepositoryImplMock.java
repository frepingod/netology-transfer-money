package ru.netology.repository;

import ru.netology.model.Card;
import ru.netology.model.request.TransferRQ;

import static ru.netology.TestData.*;

public class TransferMoneyRepositoryImplMock implements TransferMoneyRepository {

    @Override
    public Card getCard(String cardNumber) {
        if (cardNumber.equals(CARD_NUMBER_1)) {
            return CARD_1;
        }
        if (cardNumber.equals(CARD_NUMBER_2)) {
            return CARD_2;
        }
        return null;
    }

    @Override
    public int incrementAndGetOperationId() {
        return Integer.parseInt(OPERATION_ID);
    }

    @Override
    public void putTransfers(String id, TransferRQ transferRQ) {
    }

    @Override
    public void putCodes(String id, String code) {
    }

    @Override
    public TransferRQ removeTransfer(String id) {
        if (id.equals(OPERATION_ID)) {
            return TRANSFER_RQ_1_2;
        }
        return null;
    }

    @Override
    public String removeCode(String id) {
        if (id.equals(OPERATION_ID)) {
            return CODE;
        }
        return null;
    }
}