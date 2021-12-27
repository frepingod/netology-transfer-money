package ru.netology.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.netology.exception.InputDataException;
import ru.netology.model.Card;
import ru.netology.model.request.ConfirmRQ;
import ru.netology.model.request.TransferRQ;
import ru.netology.model.response.TransferAndConfirmRS;
import ru.netology.repository.TransferMoneyRepository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class TransferMoneyService {

    private final TransferMoneyRepository repository;

    private final Map<String, TransferRQ> transfers = new ConcurrentHashMap<>();
    private final Map<String, String> codes = new ConcurrentHashMap<>();
    private final AtomicInteger operationId = new AtomicInteger();

    public TransferMoneyService(TransferMoneyRepository repository) {
        this.repository = repository;
    }

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

        if (cardFrom.getAmount().getValue() < transferRQ.getAmount().getValue()) {
            log.error("card from invalid data: not enough money");
            throw new InputDataException("card from invalid data: not enough money");
        }

        final String transferId = Integer.toString(operationId.incrementAndGet());
        transfers.put(transferId, transferRQ);
        codes.put(transferId, "0000");

        return new TransferAndConfirmRS(transferId);
    }

    public TransferAndConfirmRS postConfirmOperation(ConfirmRQ confirmRQ) {
        final String operationId = confirmRQ.getOperationId();

        final TransferRQ transferRQ = transfers.remove(operationId);
        if (transferRQ == null) {
            log.error("confirm operation invalid data: operation not found");
            throw new InputDataException("confirm operation invalid data: operation not found");
        }

        final String code = codes.remove(operationId);
        if (!confirmRQ.getCode().equals(code) || code.isEmpty()) {
            log.error("confirm operation invalid data: code");
            throw new InputDataException("confirm operation invalid data: code");
        }

        final Card cardFrom = repository.getCard(transferRQ.getCardFromNumber());
        final Card cardTo = repository.getCard(transferRQ.getCardToNumber());

        final int cardFromValue = cardFrom.getAmount().getValue();
        final int cardToValue = cardTo.getAmount().getValue();
        final int transferValue = transferRQ.getAmount().getValue();
        final int commission = (int) (transferValue * 0.01);

        cardFrom.getAmount().setValue(cardFromValue - transferValue);
        cardTo.getAmount().setValue(cardToValue + transferValue - commission);

        log.info(String.format("Success transfer. Transfer operation id %s. Card from %s. Card to %s. Amount %d. Commission %d",
                operationId, transferRQ.getCardFromNumber(), transferRQ.getCardToNumber(), transferValue, commission));

        return new TransferAndConfirmRS(operationId);
    }
}