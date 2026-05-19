package com.example.java8comparison;

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
