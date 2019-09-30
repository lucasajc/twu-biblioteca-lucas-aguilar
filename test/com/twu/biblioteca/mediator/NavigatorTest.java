package com.twu.biblioteca.mediator;

import com.twu.biblioteca.domain.Book;
import com.twu.biblioteca.domain.Library;
import com.twu.biblioteca.view.Menu;
import com.twu.biblioteca.view.MenuOption;
import com.twu.biblioteca.view.Onboard;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class NavigatorTest {

    private Menu menu;
    private Library library;
    private Onboard onboard;
    private ArrayList<Book> bookList = new ArrayList<Book>();
    private Navigator navigator;

    private static final String INVALID_CHECKOUT_MESSAGE = "Please select a valid ID!";

    private void initializeMenu() {
        MenuOption listOfBooksOption = MenuOption.LIST_BOOKS;
        ArrayList<MenuOption> options = new ArrayList<MenuOption>();
        options.add(listOfBooksOption);
        menu = new Menu(options);
    }

    private void initializeLibrary() {
        bookList = new ArrayList<Book>();
        bookList.add(new Book("J. R. R. Tolkien", "The Lord of the Rings", 1954));
        bookList.add(new Book("J. R. R. Tolkien", "The Hobbit", 1937));
        library = new Library(bookList);
    }

    @Before
    public void setUp() {
        navigator = new Navigator();
        onboard = new Onboard();

        initializeLibrary();
        initializeMenu();
    }

    @Test
    public void shouldRegisterAMenuCorrectly() {
        navigator.registerComponent(menu);

        assertThat(navigator.getMenu().getClass().getSimpleName(), is("Menu"));
    }

    @Test
    public void shouldRegisterALibraryCorrectly() {
        navigator.registerComponent(library);

        assertThat(navigator.getLibrary().getClass().getSimpleName(), is("Library"));
    }

    @Test
    public void shouldRegisterAOnboardCorrectly() {
        navigator.registerComponent(onboard);

        assertThat(navigator.getOnboard().getClass().getSimpleName(), is("Onboard"));
    }

    @Test
    public void shouldListBooksWhenUserChoosesItOnMenu() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        navigator.registerComponent(menu);
        navigator.registerComponent(library);

        navigator.selectMenuOption("1");
        navigator.processUserInput();

        assertThat(outContent.toString(), containsString("The Lord of the Rings"));
        assertThat(outContent.toString(), containsString("The Hobbit"));
    }

    @Test
    public void shouldPrintAnUnsuccessfulMessageWhenCheckoutInputIsInvalid() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        navigator.registerComponent(library);

        navigator.checkoutBookById("abc");

        assertThat(outContent.toString(), containsString(INVALID_CHECKOUT_MESSAGE));
    }
}
