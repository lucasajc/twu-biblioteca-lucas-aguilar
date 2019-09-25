package com.twu.biblioteca.view;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class OnboardTest {

    private static final String WELCOME_MESSAGE = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore.\n";

    @Test
    public void shouldPrintAWelcomingMessage() {
        Onboard onboard = new Onboard();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        onboard.printWelcomeMessage();

        assertThat(outContent.toString(), is(WELCOME_MESSAGE));
    }
}
