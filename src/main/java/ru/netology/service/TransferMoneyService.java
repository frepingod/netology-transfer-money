package ru.netology.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.netology.exception.InputDataException;
import ru.netology.model.Card;
import ru.netology.model.ConfirmOperation;
import ru.netology.model.request.ConfirmRQ;
import ru.netology.model.request.TransferRQ;
import ru.netology.model.response.TransferAndConfirmRS;
import ru.netology.repository.TransferMoneyRepository;

@Service
@AllArgsConstructor
public class TransferMoneyService {

    private TransferMoneyRepository repository;

    public TransferAndConfirmRS postTransfer(TransferRQ transferRQ) {
        final Card cardFrom = repository.getCard(transferRQ.getCardFromNumber());
        final Card cardTo = repository.getCard(transferRQ.getCardToNumber());

        if (cardFrom == null && cardTo != null) {
            throw new InputDataException("card from not found");
        } else if (cardFrom != null && cardTo == null) {
            throw new InputDataException("card to not found");
        } else if (cardFrom == null && cardTo == null) {
            throw new InputDataException("card from and card to not found");
        }

        final String cardFromValidTill = cardFrom.getValidTill();
        final String cardFromCVV = cardFrom.getCvv();
        final String transferRQCardFromValidTill = transferRQ.getCardFromValidTill();
        final String transferRQCardFromCVV = transferRQ.getCardFromCVV();

        if (!cardFromValidTill.equals(transferRQCardFromValidTill) && cardFromCVV.equals(transferRQCardFromCVV)) {
            throw new InputDataException("card from invalid data: valid till");
        } else if (cardFromValidTill.equals(transferRQCardFromValidTill) && !cardFromCVV.equals(transferRQCardFromCVV)) {
            throw new InputDataException("card from invalid data: cvv");
        } else if (!cardFromValidTill.equals(transferRQCardFromValidTill) && !cardFromCVV.equals(transferRQCardFromCVV)) {
            throw new InputDataException("card from invalid data: valid till and cvv");
        }

        final int cardFromValue = cardFrom.getAmount().getValue();
        final int cardToValue = cardTo.getAmount().getValue();
        final int transferValue = transferRQ.getAmount().getValue();

        if (cardFromValue < transferValue) {
            throw new InputDataException("card from invalid data: not enough money");
        }

        cardFrom.getAmount().setValue(cardFromValue - transferValue);
        cardTo.getAmount().setValue(cardToValue + transferValue);
        final int transferId = repository.getAndIncrementTransferOperationId();

        return new TransferAndConfirmRS(Integer.toString(transferId));
    }

    public TransferAndConfirmRS postConfirmOperation(ConfirmRQ confirmRQ) {
        final ConfirmOperation operation = repository.getConfirmOperation(confirmRQ.getOperationId());

        if (operation == null) {
            throw new InputDataException("confirm operation not found");
        }

        if (!operation.getCode().equals(confirmRQ.getCode())) {
            throw new InputDataException("confirm operation invalid data: code");
        }

        return new TransferAndConfirmRS(operation.getOperationId());
    }
}