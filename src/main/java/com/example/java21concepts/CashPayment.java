package com.example.java21concepts;

/*
 * CashPayment has only one field, but still gets constructor, amount(),
 * equals(), hashCode(), and toString() automatically because it is a record.
 */
public record CashPayment(int amount) implements Payment {
}
