package com.twu.biblioteca.domain;

import com.twu.biblioteca.mediator.Component;

import java.util.*;
import java.util.stream.Collectors;

public class Library implements Component {
    private HashMap<UUID, Book> books;
    private static final String LIST_FORMAT = "%-25s%25s%25s%25s%n";
    private static final String[] LIST_HEADER = new String[] { "Id", "Author", "Title", "Year published" };
    private static final String SUCCESS_CHECKOUT_MESSAGE = "\nThank you! Enjoy the book.";

    public Library(ArrayList<Book> bookList) {
        this.books = new HashMap<UUID, Book>();
        for (Book book : bookList) {
            this.books.put(book.getId(), book);
        }
    }

    @Override
    public String getName() {
        return "Library";
    }

    public void list() {
        System.out.println("\nList of books:\n");
        System.out.format(LIST_FORMAT, LIST_HEADER);

        for(Book book : getAvailableBooks()) {
            System.out.format(LIST_FORMAT,
                    book.getId(),
                    book.getAuthor(),
                    book.getTitle(),
                    book.getYear());
        }
    }

    public void checkoutBookById(UUID id) {
        if(!books.get(id).isCheckedOut()) {
            books.get(id).setCheckedOut(true);
            System.out.println(SUCCESS_CHECKOUT_MESSAGE);
        }
    }

    public ArrayList<Book> getAvailableBooks() {
        return (ArrayList<Book>) books.values().stream()
                .filter(book -> !book.isCheckedOut())
                .collect(Collectors.toList());
    }

    public HashMap<UUID, Book> getBooks() {
        return books;
    }
}
