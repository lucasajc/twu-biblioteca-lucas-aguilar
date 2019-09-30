package com.twu.biblioteca.mediator;

public interface Mediator {
    void registerComponent(Component component);
    void printWelcomeMessage();
    void selectMenuOption(String userInput);
    void processUserInput();
    void listBooks();
    void exitApplication();
    void run();
}
