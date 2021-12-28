package ru.netology.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.netology.model.response.TransferAndConfirmRS;
import ru.netology.repository.TransferMoneyRepositoryImplMock;

import static org.junit.jupiter.api.Assertions.*;
import static ru.netology.TestData.*;

@SpringBootTest
class TransferMoneyServiceTest {

    private final TransferMoneyService service = new TransferMoneyService(new TransferMoneyRepositoryImplMock());

    @Test
    void postTransfer() {
        TransferAndConfirmRS expected = new TransferAndConfirmRS(OPERATION_ID);
        TransferAndConfirmRS actual = service.postTransfer(TRANSFER_RQ_1_2);
        assertEquals(expected, actual);
    }

    @Test
    void postConfirmOperation() {
        TransferAndConfirmRS expected = new TransferAndConfirmRS(OPERATION_ID);
        TransferAndConfirmRS actual = service.postConfirmOperation(CONFIRM_RQ);
        assertEquals(expected, actual);
    }
}