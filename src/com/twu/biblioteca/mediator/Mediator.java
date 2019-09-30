package com.twu.biblioteca.mediator;

public interface Mediator {
    void registerComponent(Component component);
    void printWelcomeMessage();
    void selectMenuOption(String userInput);
    void checkoutBookById(String userInput);
    void processUserInput();
    void listBooks();
    void startBookCheckout();
    void exitApplication();
    void run();
}
