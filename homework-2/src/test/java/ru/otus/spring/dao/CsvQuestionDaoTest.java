package ru.otus.spring.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Question;
import ru.otus.spring.service.QuestionStreamConvertServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Слой получения вопросов")
class CsvQuestionDaoTest {

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

    @DisplayName("Должен корректно получать вопросы")
    @Test
    void shouldBeCorrectGetQuestions() {
        questionDao = new CsvQuestionDao(new QuestionStreamConvertServiceImpl(), () -> "questions-test.csv");
        List<Question> resultQuestions = questionDao.getQuestions();
        assertThat(resultQuestions).isNotNull();
        assertThat(resultQuestions).isEqualTo(questions);
    }
}