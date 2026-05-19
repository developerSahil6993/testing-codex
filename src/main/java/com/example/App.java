package com.example;

public final class App {
    private App() {
    }

    public static void main(String[] args) {
        System.out.println(greeting("Codex"));
    }

    public static String greeting(String name) {
        String recipient = (name == null || name.isBlank()) ? "World" : name.trim();
        return "Hello, " + recipient + "!";
    }
}
