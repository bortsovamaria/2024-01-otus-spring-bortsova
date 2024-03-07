package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
@SpringBootTest(classes = TestServiceImpl.class)
class TestServiceImplTest {

    @MockBean
    private LocalizedIOService ioService;

    @MockBean
    private QuestionDao questionDao;

    @Autowired
    TestService testService;

    @DisplayName("Должен корректно запускать тестирование на английском языке")
    @Test
    void shouldBeCorrectExecuteTestForEnLocale() {
        given(questionDao.getQuestions()).willReturn(QuestionUtils.getQuestionsForEnLocale());
        given(ioService.readIntForRangeWithPromptLocalized(1, 5,
                "TestService.choose.answer",
                "TestService.choose.answer.input.number.incorrect"))
                .willReturn(2);

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
        given(questionDao.getQuestions()).willReturn(QuestionUtils.getQuestionsForRuLocale());
        given(ioService.readIntForRangeWithPromptLocalized(1, 5,
                "TestService.choose.answer",
                "TestService.choose.answer.input.number.incorrect"))
                .willReturn(2);

        Student student = new Student("имя", "фамилия");
        TestResult testResult = testService.executeTestFor(student);
        assertThat(testResult).isNotNull();
        assertThat(testResult.getRightAnswersCount()).isEqualTo(1);
        assertThat(testResult.getStudent()).isEqualTo(student);
        verify(questionDao, times(1)).getQuestions();
    }
}