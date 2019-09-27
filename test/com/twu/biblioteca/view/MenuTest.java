package com.twu.biblioteca.view;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MenuTest {

    private Menu menu;
    private static final String INVALID_MENU_OPTION_MESSAGE = "\nPlease select a valid option!\n\n";

    @Before
    public void setUp() {
        MenuOption listOfBooksOption = MenuOption.LIST_BOOKS;
        ArrayList<MenuOption> options = new ArrayList<MenuOption>();
        options.add(listOfBooksOption);
        menu = new Menu(options);
    }

    @Test
    public void shouldShowAMenuWithListOfBooksOption() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        menu.show();

        assertThat(outContent.toString(), containsString(MenuOption.LIST_BOOKS.getDescription()));
    }
}
