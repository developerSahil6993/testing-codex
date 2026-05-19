package com.example.java21concepts;

public record CardPayment(String maskedCardNumber, int amount) implements Payment {
}
