package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.domain.Question;
import ru.otus.spring.service.QuestionStreamConvertServiceImpl;
import ru.otus.spring.utils.QuestionUtils;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Слой получения вопросов")
class CsvQuestionDaoTest {

    @DisplayName("Должен корректно получать вопросы на английском языке")
    @Test
    void shouldBeCorrectGetQuestionsForEnLocale() {
        QuestionUtils questionUtils = new QuestionUtils();
        QuestionDao questionDao = new CsvQuestionDao(new QuestionStreamConvertServiceImpl(), () -> "questions.csv");
        List<Question> resultQuestions = questionDao.getQuestions();
        assertThat(resultQuestions).isNotNull();
        assertThat(resultQuestions).isEqualTo(questionUtils.getQuestionsForEnLocale());
    }

    @DisplayName("Должен корректно получать вопросы на русском языке")
    @Test
    void shouldBeCorrectGetQuestionsForRuLocale() {
        QuestionUtils questionUtils = new QuestionUtils();
        QuestionDao questionDao = new CsvQuestionDao(new QuestionStreamConvertServiceImpl(), () -> "questions_ru_RU.csv");
        List<Question> resultQuestions = questionDao.getQuestions();
        assertThat(resultQuestions).isNotNull();
        assertThat(resultQuestions).isEqualTo(questionUtils.getQuestionsForRuLocale());
    }
}