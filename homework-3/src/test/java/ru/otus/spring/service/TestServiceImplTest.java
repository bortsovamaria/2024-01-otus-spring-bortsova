package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Profile;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Student;
import ru.otus.spring.domain.TestResult;
import ru.otus.spring.utils.QuestionUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("Сервис тестирования студентов")
@Profile(value = "test")
class TestServiceImplTest {

    @Mock
    private LocalizedIOService ioService;

    @Mock
    private QuestionDao questionDao;

    @DisplayName("Должен корректно запускать тестирование на английском языке")
    @Test
    void shouldBeCorrectExecuteTestForEnLocale() {
        QuestionUtils questionUtils = new QuestionUtils();
        given(questionDao.getQuestions()).willReturn(questionUtils.getQuestionsForEnLocale());
        given(ioService.readIntForRangeWithPromptLocalized(1, 5,
                "TestService.choose.answer",
                "TestService.choose.answer.input.number.incorrect"))
                .willReturn(2);
        var testService = new TestServiceImpl(ioService, questionDao);

        Student student = new Student("firstname", "lastname");
        TestResult testResult = testService.executeTestFor(student);
        assertThat(testResult).isNotNull();
        assertThat(testResult.getRightAnswersCount()).isEqualTo(1);
        assertThat(testResult.getStudent()).isEqualTo(student);
        verify(questionDao, times(1)).getQuestions();
    }

    @DisplayName("Должен корректно запускать тестирование на русском языке")
    @Test
    void shouldBeCorrectExecuteTestForRuLocale() {
        QuestionUtils questionUtils = new QuestionUtils();
        given(questionDao.getQuestions()).willReturn(questionUtils.getQuestionsForRuLocale());
        given(ioService.readIntForRangeWithPromptLocalized(1, 5,
                "TestService.choose.answer",
                "TestService.choose.answer.input.number.incorrect"))
                .willReturn(2);
        var testService = new TestServiceImpl(ioService, questionDao);

        Student student = new Student("имя", "фамилия");
        TestResult testResult = testService.executeTestFor(student);
        assertThat(testResult).isNotNull();
        assertThat(testResult.getRightAnswersCount()).isEqualTo(1);
        assertThat(testResult.getStudent()).isEqualTo(student);
        verify(questionDao, times(1)).getQuestions();
    }

}