package ru.netology.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class Card {

    @NotBlank(message = "number must not be null")
    @Size(min = 19, max = 19)
    @Pattern(regexp = "[0-9]{4}[ ][0-9]{4}[ ][0-9]{4}[ ][0-9]{4}")
    private String number;

    @NotBlank(message = "valid till must not be null")
    @Size(min = 5, max = 5)
    @Pattern(regexp = "(0[1-9]|1[0-2])[/][0-9]{2}")
    private String validTill;

    @NotBlank(message = "cvv must not be null")
    @Size(min = 3, max = 3)
    @Pattern(regexp = "[0-9]{3}")
    private String cvv;

    private Amount amount;
}