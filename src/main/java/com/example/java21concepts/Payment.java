package com.example.java21concepts;

/*
 * A sealed interface restricts who can implement it.
 *
 * Only the classes listed after "permits" are valid Payment types. This is useful
 * when your domain has a known set of choices, such as payment methods, order
 * states, or event types.
 */
public sealed interface Payment permits CardPayment, UpiPayment, CashPayment {
    // Every payment type must expose its amount, so switch logic can use it safely.
    int amount();
}
