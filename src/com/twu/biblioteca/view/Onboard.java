package com.twu.biblioteca.view;

import com.twu.biblioteca.mediator.Component;
import com.twu.biblioteca.mediator.Mediator;

public class Onboard implements Component {
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

    @Override
    public String getName() {
        return "Onboard";
    }
}
