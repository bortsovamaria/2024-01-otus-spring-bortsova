package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.Student;
import ru.otus.spring.domain.TestResult;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RequiredArgsConstructor
@Service
public class TestServiceImpl implements TestService {

    private final LocalizedIOService ioService;

    private final QuestionDao questionDao;

    @Override
    public TestResult executeTestFor(Student student) {
        var questions = questionDao.getQuestions();
        TestResult testResult = new TestResult(student);
        executeTest(questions, testResult);
        return testResult;
    }

    private void executeTest(List<Question> questions, TestResult testResult) {
        ioService.printLineLocalized("TestService.answer.the.questions");
        for (Question question : questions) {
            printQuestion(question);
            printAnswersOfQuestion(question);
            divideLine();
            acceptAnswerFromStudent(testResult, question, question.answers());
            divideLine();
        }
    }

    private void divideLine() {
        ioService.printLine("");
    }

    private void printQuestion(Question question) {
        ioService.printFormattedLine(question.text());
    }

    private void printAnswersOfQuestion(Question question) {
        AtomicInteger numberAnswer = new AtomicInteger();
        question.answers().forEach(a ->
                ioService.printFormattedLine(numberAnswer.incrementAndGet() + ") " + a.text()));
    }

    private void acceptAnswerFromStudent(TestResult testResult, Question question, List<Answer> answers) {
        int numberEntered = ioService.readIntForRangeWithPromptLocalized(1, 5,
                "TestService.choose.answer",
                "TestService.choose.answer.input.number.incorrect");
        testResult.applyAnswer(question, answers.get(numberEntered - 1).isCorrect());
    }
}