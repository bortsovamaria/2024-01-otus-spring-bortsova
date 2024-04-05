package ru.otus.spring.homework7.commands;

import org.h2.tools.Console;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.sql.SQLException;

@ShellComponent
public class AdminCommands {

    @ShellMethod(value = "Console", key = "console")
    public void console() throws SQLException {
        Console.main();
    }
}
