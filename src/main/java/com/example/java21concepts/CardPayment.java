package com.example.java21concepts;

/*
 * This record is one permitted implementation of Payment.
 * Records work well with sealed types because each variant can be tiny and clear.
 */
public record CardPayment(String maskedCardNumber, int amount) implements Payment {
}
