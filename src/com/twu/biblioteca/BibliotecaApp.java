package com.twu.biblioteca;

import com.twu.biblioteca.domain.Book;
import com.twu.biblioteca.domain.Library;
import com.twu.biblioteca.menu.Menu;
import com.twu.biblioteca.menu.MenuConstants;
import com.twu.biblioteca.menu.MenuOption;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class BibliotecaApp {
    private static Library library;
    private static Menu menu;

    private static final String INVALID_CHECKOUT_MESSAGE = "Please select a valid ID!";

    public static void main(String[] args) {
        menu = initializeMenu();
        library = initializeBookCollection();

        printWelcomeMessage();
        run();
    }

    private static Menu initializeMenu() {
        ArrayList<MenuOption> options = new ArrayList<MenuOption>();
        options.add(MenuOption.LIST_BOOKS);
        options.add(MenuOption.CHECKOUT_BOOK);
        options.add(MenuOption.EXIT_APPLICATION);
        Menu menu = new Menu(options);

        return menu;
    }

    private static Library initializeBookCollection() {
        Library library;
        ArrayList<Book> bookList = new ArrayList<Book>();
        bookList = new ArrayList<Book>();
        bookList.add(new Book("J. R. R. Tolkien", "The Lord of the Rings", 1954));
        bookList.add(new Book("J. R. R. Tolkien", "The Hobbit", 1937));
        bookList.add(new Book("J. R. R. Tolkien", "The Silmarillion", 1977));
        bookList.add(new Book("J. R. R. Tolkien", "The Fall of Gondolin", 2018));
        bookList.add(new Book("Conn Iggulden", "Wolf of the Plains", 2007));
        bookList.add(new Book("Conn Iggulden", "Lords of the Bow", 2008));
        bookList.add(new Book("Conn Iggulden", "Bones of the Hills", 2008));
        bookList.add(new Book("Conn Iggulden", "Conqueror", 2011));
        bookList.add(new Book("George R. R. Martin", "A Game of Thrones", 1996));
        bookList.add(new Book("George R. R. Martin", "A Clash of Kings", 1999));
        bookList.add(new Book("George R. R. Martin", "A Storm of Swords", 2000));
        bookList.add(new Book("George R. R. Martin", "A Feast for Crows", 2005));
        bookList.add(new Book("George R. R. Martin", "A Dance with Dragons", 2011));

        library = new Library(bookList);

        return library;
    }

    public static void printWelcomeMessage() {
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore.");
    }

    public static void selectMenuOption(String userInput) {
        menu.selectMenuOption(userInput);
    }

    public static void checkoutBookById(String userInput) {
        try {
            library.checkoutBookById(UUID.fromString(userInput));
        } catch (IllegalArgumentException e) {
            System.out.println("\n" + INVALID_CHECKOUT_MESSAGE);
        }
    }

    public static void processUserInput() {
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

    public static void listBooks() {
        library.list();
    }

    public static void startBookCheckout() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("> Insert a book ID to checkout it: ");
        String userInput = scanner.nextLine();

        checkoutBookById(userInput);
    }

    public static void exitApplication() {
        System.exit(0);
    }

    public static void run() {
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

    public static void setLibrary(Library library) {
        BibliotecaApp.library = library;
    }

    public static void setMenu(Menu menu) {
        BibliotecaApp.menu = menu;
    }
}
