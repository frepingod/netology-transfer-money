package ru.netology.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.netology.exception.InputDataException;
import ru.netology.model.Card;
import ru.netology.model.ConfirmOperation;
import ru.netology.model.request.ConfirmRQ;
import ru.netology.model.request.TransferRQ;
import ru.netology.model.response.TransferAndConfirmRS;
import ru.netology.repository.TransferMoneyRepository;

@Service
@Slf4j
@AllArgsConstructor
public class TransferMoneyService {

    private TransferMoneyRepository repository;

    public TransferAndConfirmRS postTransfer(TransferRQ transferRQ) {
        final Card cardFrom = repository.getCard(transferRQ.getCardFromNumber());
        final Card cardTo = repository.getCard(transferRQ.getCardToNumber());

        if (cardFrom == null && cardTo != null) {
            log.error("card from not found");
            throw new InputDataException("card from not found");
        } else if (cardFrom != null && cardTo == null) {
            log.error("card to not found");
            throw new InputDataException("card to not found");
        } else if (cardFrom == null && cardTo == null) {
            log.error("card from and card to not found");
            throw new InputDataException("card from and card to not found");
        }

        if (cardFrom == cardTo) {
            log.error("two identical card numbers");
            throw new InputDataException("two identical card numbers");
        }

        final String cardFromValidTill = cardFrom.getValidTill();
        final String cardFromCVV = cardFrom.getCvv();
        final String transferRQCardFromValidTill = transferRQ.getCardFromValidTill();
        final String transferRQCardFromCVV = transferRQ.getCardFromCVV();

        if (!cardFromValidTill.equals(transferRQCardFromValidTill) && cardFromCVV.equals(transferRQCardFromCVV)) {
            log.error("card from invalid data: valid till");
            throw new InputDataException("card from invalid data: valid till");
        } else if (cardFromValidTill.equals(transferRQCardFromValidTill) && !cardFromCVV.equals(transferRQCardFromCVV)) {
            log.error("card from invalid data: cvv");
            throw new InputDataException("card from invalid data: cvv");
        } else if (!cardFromValidTill.equals(transferRQCardFromValidTill) && !cardFromCVV.equals(transferRQCardFromCVV)) {
            log.error("card from invalid data: valid till and cvv");
            throw new InputDataException("card from invalid data: valid till and cvv");
        }

        final int cardFromValue = cardFrom.getAmount().getValue();
        final int cardToValue = cardTo.getAmount().getValue();
        final int transferValue = transferRQ.getAmount().getValue();
        final int commission = (int) (transferValue * 0.01);

        if (cardFromValue < transferValue) {
            log.error("card from invalid data: not enough money");
            throw new InputDataException("card from invalid data: not enough money");
        }

        cardFrom.getAmount().setValue(cardFromValue - transferValue);
        cardTo.getAmount().setValue(cardToValue + transferValue - commission);
        final int transferId = repository.incrementAndGetTransferOperationId();

        log.info(String.format("Success transfer. Transfer operation id %d. Card from %s. Card to %s. Amount %d. Commission %d",
                transferId, transferRQ.getCardFromNumber(), transferRQ.getCardToNumber(), transferValue, commission));

        return new TransferAndConfirmRS(Integer.toString(transferId));
    }

    public TransferAndConfirmRS postConfirmOperation(ConfirmRQ confirmRQ) {
        final ConfirmOperation operation = repository.getConfirmOperation(confirmRQ.getOperationId());

        if (operation == null) {
            log.error("confirm operation not found");
            throw new InputDataException("confirm operation not found");
        }

        if (!operation.getCode().equals(confirmRQ.getCode())) {
            log.error("confirm operation invalid data: code");
            throw new InputDataException("confirm operation invalid data: code");
        }

        final String confirmOperationId = operation.getOperationId();

        log.info("Success confirmation. Confirm operation id " + confirmOperationId);

        return new TransferAndConfirmRS(confirmOperationId);
    }
}