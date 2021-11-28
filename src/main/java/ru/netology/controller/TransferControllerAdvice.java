package ru.netology.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.netology.exception.ConfirmationException;
import ru.netology.exception.InputDataException;
import ru.netology.exception.TransferException;
import ru.netology.model.ErrorResponse;

public class TransferControllerAdvice {

    private static final int DUMMY = 1;

    @ExceptionHandler(InputDataException.class)
    public ResponseEntity<ErrorResponse> handleInputData(InputDataException e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage(), DUMMY));
    }

    @ExceptionHandler(TransferException.class)
    public ResponseEntity<ErrorResponse> handleTransfer(TransferException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(e.getMessage(), DUMMY));
    }

    @ExceptionHandler(ConfirmationException.class)
    public ResponseEntity<ErrorResponse> handleConfirmation(ConfirmationException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(e.getMessage(), DUMMY));
    }
}