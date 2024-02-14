package ru.otus.spring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.Student;
import ru.otus.spring.domain.TestResult;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Сервис тестирования студентов")
class TestServiceImplTest {

    @Mock
    private  IOService ioService;

    @Mock
    private QuestionDao questionDao;

    private List<Question> questions;

    @BeforeEach
    public void setUp() {
        List<Answer> listAnswerForFirstQuestion = new ArrayList<>();
        listAnswerForFirstQuestion.add(new Answer("answer 1", true));
        listAnswerForFirstQuestion.add(new Answer("answer 2", false));
        listAnswerForFirstQuestion.add(new Answer("answer 3", false));

        List<Answer> listAnswerForSecondQuestion = new ArrayList<>();
        listAnswerForSecondQuestion.add(new Answer("answer 1", false));
        listAnswerForSecondQuestion.add(new Answer("answer 2", true));
        listAnswerForSecondQuestion.add(new Answer("answer 3", false));

        questions = new ArrayList<>();
        questions.add(new Question("question 1", listAnswerForFirstQuestion));
        questions.add(new Question("question 2", listAnswerForSecondQuestion));
    }

    @DisplayName("Должен корректно запускать тестирование")
    @Test
    void shouldBeCorrectExecuteTestFor() {
        given(questionDao.getQuestions()).willReturn(questions);
        given(ioService.readIntForRangeWithPrompt(1, 5,
                "Choose answer:", "Invalid response number entered"))
                .willReturn(2);
        var testService = new TestServiceImpl(ioService, questionDao);

        Student student = new Student("firstname", "lastname");
        TestResult testResult = testService.executeTestFor(student);
        assertThat(testResult).isNotNull();
        assertThat(testResult.getRightAnswersCount()).isEqualTo(1);
        assertThat(testResult.getStudent()).isEqualTo(student);
        verify(questionDao, times(1)).getQuestions();
    }
}