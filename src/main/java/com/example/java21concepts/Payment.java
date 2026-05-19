package com.example.java21concepts;

public sealed interface Payment permits CardPayment, UpiPayment, CashPayment {
    int amount();
}
