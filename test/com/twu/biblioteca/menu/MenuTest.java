package com.twu.biblioteca.menu;

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

    @Test
    public void shouldSelectAMenuOptionWhenUserChoosesIt() {
        menu.selectMenuOption("1");

        assertThat(menu.getSelectedOption(), is(1));
    }

    @Test
    public void shouldDetectValidUserInput() {
        menu.selectMenuOption("1");

        assertThat(menu.isSelectedOptionValid(), is(true));
    }

    @Test
    public void shouldDetectInvalidUserInputType() {
        menu.selectMenuOption("a");

        assertThat(menu.isSelectedOptionValid(), is(false));
    }

    @Test
    public void shouldDetectInvalidUserInputOption() {
        menu.selectMenuOption("999");

        assertThat(menu.isSelectedOptionValid(), is(false));
    }

    @Test
    public void shouldPrintAMessageWhenDetectsInvalidUserInputType() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        menu.selectMenuOption("a");

        assertThat(outContent.toString(), is(INVALID_MENU_OPTION_MESSAGE));
    }

    @Test
    public void shouldPrintAMessageWhenDetectsInvalidUserInputOption() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        menu.selectMenuOption("999");

        assertThat(outContent.toString(), is(INVALID_MENU_OPTION_MESSAGE));
    }
}
