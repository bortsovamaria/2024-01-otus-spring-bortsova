package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.Student;
import ru.otus.spring.domain.TestResult;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TestServiceImpl implements TestService {

    private final IOService ioService;

    private final QuestionDao questionDao;

    public TestServiceImpl(IOService ioService, QuestionDao questionDao) {
        this.ioService = ioService;
        this.questionDao = questionDao;
    }

    @Override
    public TestResult executeTestFor(Student student) {
        var questions = questionDao.getQuestions();
        TestResult testResult = new TestResult(student);
        executeTest(questions, testResult);
        return testResult;
    }

    private void executeTest(List<Question> questions, TestResult testResult) {
        ioService.printFormattedLine("Please answer the questions below%n");
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
        int numberEntered = ioService.readIntForRangeWithPrompt(1, 5,
                "Choose answer:", "Invalid response number entered");
        testResult.applyAnswer(question, answers.get(numberEntered - 1).isCorrect());
    }
}