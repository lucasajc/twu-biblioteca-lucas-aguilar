package com.twu.biblioteca.domain;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class LibraryTest {

    private Library library;
    private ArrayList<Book> bookList = new ArrayList<Book>();

    private static final String SUCCESS_CHECKOUT_MESSAGE = "Thank you! Enjoy the book.";

    @Before
    public void setUp() {
        bookList = new ArrayList<Book>();
        bookList.add(new Book("J. R. R. Tolkien", "The Lord of the Rings", 1954));
        bookList.add(new Book("J. R. R. Tolkien", "The Hobbit", 1937));
        library = new Library(bookList);
    }

    @Test
    public void shouldCreateACollectionFromAListOfBooks() {
        assertThat(library.getBooks().size(), is(2));
    }

    @Test
    public void shouldListEntireBookCollection() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        library.list();

        assertThat(outContent.toString(), containsString("The Lord of the Rings"));
        assertThat(outContent.toString(), containsString("The Hobbit"));
    }

    @Test
    public void shouldListABookAuthor() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        library.list();

        assertThat(outContent.toString(), containsString("J. R. R. Tolkien"));
    }

    @Test
    public void shouldListABookYear() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        library.list();

        assertThat(outContent.toString(), containsString(String.valueOf(1954)));
    }

    @Test
    public void shouldCheckoutABook() {
        HashMap.Entry<UUID, Book> entry = library.getBooks().entrySet().iterator().next();
        UUID key = entry.getKey();

        library.checkoutBookById(key);

        assertThat(library.getBooks().get(key).isCheckedOut(), is(true));
    }

    @Test
    public void shouldNotListACheckedOutBook() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        HashMap.Entry<UUID, Book> entry = library.getBooks().entrySet().iterator().next();
        UUID key = entry.getKey();

        library.checkoutBookById(key);
        library.list();

        assertThat(outContent.toString(), not(containsString(key.toString())));
    }

    @Test
    public void shouldPrintASuccessMessageOnCheckoutABook() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        HashMap.Entry<UUID, Book> entry = library.getBooks().entrySet().iterator().next();
        UUID key = entry.getKey();

        library.checkoutBookById(key);

        assertThat(outContent.toString(), containsString(SUCCESS_CHECKOUT_MESSAGE));
    }
}
