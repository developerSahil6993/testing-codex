package com.example.java8comparison;

public final class UpiPayment8 implements Payment8 {
    private final String upiId;
    private final int amount;

    public UpiPayment8(String upiId, int amount) {
        this.upiId = upiId;
        this.amount = amount;
    }

    public String getUpiId() {
        return upiId;
    }

    @Override
    public int getAmount() {
        return amount;
    }
}
