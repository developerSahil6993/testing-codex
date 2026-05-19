package com.example.java21concepts;

/*
 * Another Payment variant. Because Payment is sealed, adding this type requires
 * listing it in Payment permits and handling it in the switch expression.
 */
public record UpiPayment(String upiId, int amount) implements Payment {
}
