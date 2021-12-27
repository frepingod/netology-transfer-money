package ru.netology.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.model.request.TransferRQ;
import ru.netology.model.request.ConfirmRQ;
import ru.netology.model.response.TransferAndConfirmRS;
import ru.netology.service.TransferMoneyService;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "${cross.origin.host.name}", maxAge = 3600)
public class TransferMoneyController {

    private TransferMoneyService service;

    @PostMapping("/transfer")
    public TransferAndConfirmRS postTransfer(@RequestBody TransferRQ transferRequest) {
        return service.postTransfer(transferRequest);
    }

    @PostMapping("/confirmOperation")
    public TransferAndConfirmRS postConfirmOperation(@RequestBody ConfirmRQ confirmRequest) {
        return service.postConfirmOperation(confirmRequest);
    }
}