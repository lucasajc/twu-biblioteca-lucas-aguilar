package com.twu.biblioteca.view;

public class Onboard {
    private String welcomeMessage;

    public Onboard() {
        this.welcomeMessage = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore.";
    }

    public Onboard(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    public void printWelcomeMessage() {
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore.");
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }
}
