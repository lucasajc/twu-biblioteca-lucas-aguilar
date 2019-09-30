package com.twu.biblioteca.domain;

import com.twu.biblioteca.mediator.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Library implements Component {
    private HashMap<UUID, Book> books;
    private static final String LIST_FORMAT = "%-25s%25s%25s%25s%n";
    private static final String[] LIST_HEADER = new String[] { "Id", "Author", "Title", "Year published" };

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

        for(Book book : this.books.values()) {
            System.out.format(LIST_FORMAT,
                    book.getId(),
                    book.getAuthor(),
                    book.getTitle(),
                    book.getYear());
        }
    }

    public HashMap<UUID, Book> getBooks() {
        return books;
    }
}
