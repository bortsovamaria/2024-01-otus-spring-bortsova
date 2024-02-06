package ru.otus.homework.service;

import lombok.RequiredArgsConstructor;
import ru.otus.homework.dao.QuestionDao;

@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final QuestionDao dao;

    private final OutputConsoleService outputService;

    @Override
    public void executeTest() {
        var questions = dao.getAllQuestionsWithAnswers();
        outputService.print(questions);
    }
}
