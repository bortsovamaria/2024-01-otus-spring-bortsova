package ru.otus.spring.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.security.LoginContext;
import ru.otus.spring.service.TestRunnerService;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationCommands {

    private final TestRunnerService testRunnerService;

    private final LoginContext loginContext;

    @ShellMethod(value = "Login command", key = {"l", "login"})
    public String login(@ShellOption(defaultValue = "Robot")String userName) {
        loginContext.login(userName);
        return "Добро пожаловать!\n"
                + "Если хотите пройти тестирование введите команду rt или run test.";
    }

    @ShellMethod(value = "Run test", key = {"rt", "run test"})
    @ShellMethodAvailability(value = "isTestCommandAvailable")
    public void runTest() {
        testRunnerService.run();
    }

    private Availability isTestCommandAvailable() {
        return loginContext.isUserLoggedIn()
                ? Availability.available()
                : Availability.unavailable("Сначала залогиньтесь!");
    }

    @ShellMethod(value = "Get info", key = {"gi", "get info"})
    public String getInfo() {
        return "Данное приложение создано для тестирования студентов.\n"
                + "Если хотите пройти тестирование, залогиньтесь (l или login, затем введите команду rt или run test";
    }
}
