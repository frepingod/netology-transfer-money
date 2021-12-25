package ru.netology.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.netology.exception.ConfirmException;
import ru.netology.exception.InputDataException;
import ru.netology.exception.TransferException;
import ru.netology.model.response.ErrorRS;

import java.util.concurrent.atomic.AtomicInteger;

public class TransferMoneyControllerAdvice {

    private final AtomicInteger id = new AtomicInteger(1);

    @ExceptionHandler(InputDataException.class)
    public ResponseEntity<ErrorRS> handleInputData(InputDataException e) {
        return ResponseEntity.badRequest().body(new ErrorRS(e.getMessage(), getAndIncrementId()));
    }

    @ExceptionHandler(TransferException.class)
    public ResponseEntity<ErrorRS> handleTransfer(TransferException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorRS(e.getMessage(), getAndIncrementId()));
    }

    @ExceptionHandler(ConfirmException.class)
    public ResponseEntity<ErrorRS> handleConfirmation(ConfirmException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorRS(e.getMessage(), getAndIncrementId()));
    }

    private int getAndIncrementId() {
        return id.getAndIncrement();
    }
}