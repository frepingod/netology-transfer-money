package ru.netology.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorRS {

    private String message;
    private Integer id;
}