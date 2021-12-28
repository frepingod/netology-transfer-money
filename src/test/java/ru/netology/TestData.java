package ru.netology;

import ru.netology.model.Amount;
import ru.netology.model.Card;
import ru.netology.model.request.ConfirmRQ;
import ru.netology.model.request.TransferRQ;

public class TestData {

    public static final String CARD_NUMBER_1 = "1111111111111111";
    public static final String CARD_VALID_TILL_1 = "08/26";
    public static final String CARD_CVV_1 = "123";
    public static final Card CARD_1 = new Card(CARD_NUMBER_1, CARD_VALID_TILL_1, CARD_CVV_1, new Amount(1000_00, "RUR"));

    public static final String CARD_NUMBER_2 = "2222222222222222";
    public static final String CARD_VALID_TILL_2 = "08/27";
    public static final String CARD_CVV_2 = "124";
    public static final Card CARD_2 = new Card(CARD_NUMBER_2, CARD_VALID_TILL_2, CARD_CVV_2, new Amount(1000_00, "RUR"));

    public static final String CARD_NUMBER_3 = "3333333333333333";
    public static final String CARD_VALID_TILL_3 = "08/28";
    public static final String CARD_CVV_3 = "125";
    public static final Card CARD_3 = new Card(CARD_NUMBER_3, CARD_VALID_TILL_3, CARD_CVV_3, new Amount(1000_00, "RUR"));

    public static final String CARD_NUMBER_4 = "4444444444444444";
    public static final String CARD_VALID_TILL_4 = "08/29";
    public static final String CARD_CVV_4 = "126";

    public static final String CARD_NUMBER_5 = "5555555555555555";

    public static final String OPERATION_ID = "1";
    public static final String CODE = "0000";

    public static final TransferRQ TRANSFER_RQ_1_2 = new TransferRQ(CARD_NUMBER_1, CARD_VALID_TILL_1, CARD_CVV_1, CARD_NUMBER_2, new Amount(1000_00, "RUR"));
    public static final TransferRQ TRANSFER_RQ_4_5 = new TransferRQ(CARD_NUMBER_4, CARD_VALID_TILL_4, CARD_CVV_4, CARD_NUMBER_5, new Amount(1000_00, "RUR"));
    public static final ConfirmRQ CONFIRM_RQ = new ConfirmRQ(OPERATION_ID, CODE);
}