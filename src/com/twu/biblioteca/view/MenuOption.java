package com.twu.biblioteca.view;

public enum MenuOption {
    LIST_BOOKS(MenuConstants.LIST_BOOKS_KEY,"List of books"),
    EXIT_APPLICATION(MenuConstants.EXIT_APPLICATION_KEY, "Exit application");

    private int menuKey;
    private String description;

    MenuOption(int menuKey, String description) {
        this.menuKey = menuKey;
        this.description = description;
    }

    public int getMenuKey() {
        return menuKey;
    }

    public String getDescription() {
        return description;
    }
}
