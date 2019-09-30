package com.twu.biblioteca.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.stream.Collectors;

public class Library {
    private HashMap<UUID, Book> books;
    private static final String LIST_FORMAT = "%-25s%25s%25s%25s%n";
    private static final String[] LIST_HEADER = new String[] { "Id", "Author", "Title", "Year published" };
    private static final String SUCCESS_CHECKOUT_MESSAGE = "\nThank you! Enjoy the book.";
    private static final String UN_SUCCESS_CHECKOUT_MESSAGE = "Sorry, that book is not available.";
    private static final String SUCCESS_RETURN_MESSAGE = "Thank you for returning the book.";
    private static final String UN_SUCCESS_RETURN_MESSAGE = "This is not a valid book to return.";

    public Library(ArrayList<Book> bookList) {
        this.books = new HashMap<UUID, Book>();
        for (Book book : bookList) {
            this.books.put(book.getId(), book);
        }
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
        try {
            if(!books.get(id).isCheckedOut()) {
                books.get(id).setCheckedOut(true);
                System.out.println(SUCCESS_CHECKOUT_MESSAGE);
            }
        } catch (NullPointerException e) {
            System.out.println(UN_SUCCESS_CHECKOUT_MESSAGE);
        }
    }

    public void returnBookById(UUID id) {
        try {
            if(books.get(id).isCheckedOut()) {
                books.get(id).setCheckedOut(false);
                System.out.println(SUCCESS_RETURN_MESSAGE);
            }
        } catch (NullPointerException e) {
            System.out.println(UN_SUCCESS_RETURN_MESSAGE);
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
