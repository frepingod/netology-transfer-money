package ru.netology.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.model.Card;
import ru.netology.model.ConfirmRequest;
import ru.netology.model.Response;

@RestController
public class TransferController {

    @PostMapping("/transfer")
    public Response postTransfer(@RequestBody Card card) {
        return null;
    }

    @PostMapping("/confirmOperation")
    public Response postConfirmOperation(@RequestBody ConfirmRequest confirmRequest) {
        return null;
    }
}