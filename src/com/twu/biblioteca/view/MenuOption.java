package com.twu.biblioteca.view;

public enum MenuOption {
    LIST_BOOKS(1,"List of books");

    private int menuKey;
    private String description;

    MenuOption(int menuKey, String description) {
        this.menuKey = menuKey;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
