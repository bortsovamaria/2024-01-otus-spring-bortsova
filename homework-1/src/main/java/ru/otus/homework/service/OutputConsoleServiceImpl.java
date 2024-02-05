package ru.otus.homework.service;

import lombok.RequiredArgsConstructor;
import ru.otus.homework.domain.Question;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RequiredArgsConstructor
public class OutputConsoleServiceImpl implements OutputConsoleService {
    private final IOService ioService;

    @Override
    public void print(List<Question> questions) {
        ioService.printLine("");
        ioService.printFormattedLine("Please answer the questions below%n");

        questions.forEach(
                q -> {
                    ioService.printFormattedLine(q.text());
                    AtomicInteger i = new AtomicInteger();
                    q.answers().forEach(
                            a -> ioService.printLine(i.incrementAndGet() + ") " + a.text())
                    );
                    ioService.printLine("");
                }
        );
    }
}
