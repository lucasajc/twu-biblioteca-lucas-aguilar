package com.twu.biblioteca.mediator;

import com.twu.biblioteca.domain.Library;
import com.twu.biblioteca.view.Menu;
import com.twu.biblioteca.view.MenuConstants;
import com.twu.biblioteca.view.Onboard;

import java.util.Scanner;
import java.util.UUID;

public class Navigator implements Mediator {
    private Library library;
    private Menu menu;
    private Onboard onboard;

    private static final String LIBRARY = "Library";
    private static final String MENU = "Menu";
    private static final String ONBOARD = "Onboard";
    private static final String INVALID_CHECKOUT_MESSAGE = "Please select a valid ID!";

    @Override
    public void registerComponent(Component component) {
        switch (component.getName()) {
            case LIBRARY:
                library = (Library)component;
                break;
            case MENU:
                menu = (Menu)component;
                break;
            case ONBOARD:
                onboard = (Onboard)component;
                break;
        }
    }

    @Override
    public void printWelcomeMessage() {
        onboard.printWelcomeMessage();
    }

    @Override
    public void selectMenuOption(String userInput) {
        menu.selectMenuOption(userInput);
    }

    @Override
    public void checkoutBookById(String userInput) {
        try {
            library.checkoutBookById(UUID.fromString(userInput));
        } catch (IllegalArgumentException e) {
            System.out.println("\n" + INVALID_CHECKOUT_MESSAGE);
        }
    }

    @Override
    public void processUserInput() {
        switch(menu.getSelectedOption()) {
            case MenuConstants.LIST_BOOKS_KEY:
                listBooks();
                break;
            case MenuConstants.CHECKOUT_BOOK_KEY:
                startBookCheckout();
                break;
            case MenuConstants.EXIT_APPLICATION_KEY:
                exitApplication();
        }
    }

    @Override
    public void listBooks() {
        library.list();
    }

    @Override
    public void startBookCheckout() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("> Insert a book ID to checkout it: ");
        String userInput = scanner.nextLine();

        checkoutBookById(userInput);
    }

    @Override
    public void exitApplication() {
        System.exit(0);
    }

    @Override
    public void run() {
        menu.show();
        do {
            menu.printMenuSelectUserInput();
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();
            selectMenuOption(userInput);
        } while(!menu.isSelectedOptionValid());

        processUserInput();
        run();
    }

    Library getLibrary() {
        return library;
    }

    Menu getMenu() {
        return menu;
    }

    Onboard getOnboard() {
        return onboard;
    }
}
