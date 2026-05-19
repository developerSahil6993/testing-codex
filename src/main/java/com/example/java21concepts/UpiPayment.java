package com.example.java21concepts;

public record UpiPayment(String upiId, int amount) implements Payment {
}
