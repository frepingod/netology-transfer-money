package ru.netology.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferAndConfirmRS {

    @NotBlank(message = "operation id must not be null")
    @Pattern(regexp = "^[0-9]*$")
    private String operationId;
}