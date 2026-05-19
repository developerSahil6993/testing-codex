package com.example.java8comparison;

/*
 * Java 8 version of CashPayment.
 * Even a one-field model needs a field, constructor, and getter in this style.
 */
public final class CashPayment8 implements Payment8 {
    private final int amount;

    public CashPayment8(int amount) {
        this.amount = amount;
    }

    @Override
    public int getAmount() {
        return amount;
    }
}
