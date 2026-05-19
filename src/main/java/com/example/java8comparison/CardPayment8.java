package com.example.java8comparison;

public final class CardPayment8 implements Payment8 {
    private final String maskedCardNumber;
    private final int amount;

    public CardPayment8(String maskedCardNumber, int amount) {
        this.maskedCardNumber = maskedCardNumber;
        this.amount = amount;
    }

    public String getMaskedCardNumber() {
        return maskedCardNumber;
    }

    @Override
    public int getAmount() {
        return amount;
    }
}
