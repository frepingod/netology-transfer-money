package ru.netology.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ConfirmRequest extends Response {

    private String code;

    public ConfirmRequest(String operationId, String code) {
        super(operationId);
        this.code = code;
    }
}