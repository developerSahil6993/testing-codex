package com.example.java8comparison;

/*
 * Java 8 style interface.
 *
 * Unlike a Java 21 sealed interface, this interface is open: any class in any
 * package can implement it. That flexibility is useful sometimes, but it also
 * means the compiler cannot check that all payment types are handled.
 */
public interface Payment8 {
    int getAmount();
}
