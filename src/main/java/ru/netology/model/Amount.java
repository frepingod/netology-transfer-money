package ru.netology.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class Amount {

    @Min(0)
    private Integer value;

    @NotBlank(message = "currency must not be null")
    private String currency;
}