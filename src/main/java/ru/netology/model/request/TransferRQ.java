package ru.netology.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.netology.model.Amount;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class TransferRQ {

    @NotBlank(message = "card from number must not be null")
    @Size(min = 19, max = 19)
    @Pattern(regexp = "[0-9]{4}[ ][0-9]{4}[ ][0-9]{4}[ ][0-9]{4}")
    private String cardFromNumber;

    @NotBlank(message = "card from valid till must not be null")
    @Size(min = 5, max = 5)
    @Pattern(regexp = "(0[1-9]|1[0-2])[/][0-9]{2}")
    private String cardFromValidTill;

    @NotBlank(message = "card from cvv must not be null")
    @Size(min = 3, max = 3)
    @Pattern(regexp = "[0-9]{3}")
    private String cardFromCVV;

    @NotBlank(message = "card to number must not be null")
    @Size(min = 19, max = 19)
    @Pattern(regexp = "[0-9]{4}[ ][0-9]{4}[ ][0-9]{4}[ ][0-9]{4}")
    private String cardToNumber;

    private Amount amount;
}