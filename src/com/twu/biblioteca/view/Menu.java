package com.twu.biblioteca.view;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private ArrayList<MenuOption> options;
    private int selectedOption;
    private boolean isSelectedOptionValid;

    public Menu(ArrayList<MenuOption> options) {
        this.options = options;
        this.selectedOption = 0;
        this.isSelectedOptionValid = false;
    }

    public void show() {
        System.out.println("\n---------------- Menu ----------------");
        for(MenuOption option : this.options) {
            System.out.println("[" + option.getMenuKey() + "] " + option.getDescription());
        }
        System.out.println("--------------------------------------\n");
    }

    public void printMenuSelectUserInput() {
        System.out.print("> Select a menu option: ");
    }
}
