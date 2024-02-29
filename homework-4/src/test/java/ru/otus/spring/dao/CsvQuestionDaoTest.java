package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.config.TestFileNameProvider;
import ru.otus.spring.domain.Question;
import ru.otus.spring.service.QuestionStreamConvertServiceImpl;
import ru.otus.spring.utils.QuestionUtils;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

@DisplayName("Слой получения вопросов")
@SpringBootTest(classes = {CsvQuestionDao.class, QuestionStreamConvertServiceImpl.class})
class CsvQuestionDaoTest {

    @Autowired
    QuestionDao questionDao;

    @MockBean
    TestFileNameProvider testFileNameProvider;

    @DisplayName("Должен корректно получать вопросы на английском языке")
    @Test
    void shouldBeCorrectGetQuestionsForEnLocale() {
        given(testFileNameProvider.getTestFileName()).willReturn("questions.csv");
        List<Question> resultQuestions = questionDao.getQuestions();
        assertThat(resultQuestions).isNotNull();
        assertThat(resultQuestions).isEqualTo(QuestionUtils.getQuestionsForEnLocale());
    }

    @DisplayName("Должен корректно получать вопросы на русском языке")
    @Test
    void shouldBeCorrectGetQuestionsForRuLocale() {
        given(testFileNameProvider.getTestFileName()).willReturn("questions_ru_RU.csv");

        List<Question> resultQuestions = questionDao.getQuestions();
        assertThat(resultQuestions).isNotNull();
        assertThat(resultQuestions).isEqualTo(QuestionUtils.getQuestionsForRuLocale());
    }
}