package ru.netology.repository;

import ru.netology.model.Card;

public interface TransferMoneyRepository {

    Card getCard(String cardNumber);
}