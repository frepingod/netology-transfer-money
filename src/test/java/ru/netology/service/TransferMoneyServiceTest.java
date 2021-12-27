package ru.netology.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.netology.model.Amount;
import ru.netology.model.request.ConfirmRQ;
import ru.netology.model.request.TransferRQ;
import ru.netology.model.response.TransferAndConfirmRS;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransferMoneyServiceTest {

    private final TransferRQ testTransferRQ = new TransferRQ(
            "1111111111111111", "08/26", "123", "2222222222222222",
            new Amount(10000, "RUR")
    );

    private static final String OPERATION_ID = "1";

    @Autowired
    private TransferMoneyService service;

    @Test
    void postTransfer() {
        TransferAndConfirmRS expected = new TransferAndConfirmRS(OPERATION_ID);
        TransferAndConfirmRS actual = service.postTransfer(testTransferRQ);
        assertEquals(expected, actual);
    }
}