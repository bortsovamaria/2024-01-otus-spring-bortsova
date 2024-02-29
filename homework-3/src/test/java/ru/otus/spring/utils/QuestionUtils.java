package ru.otus.spring.utils;

import lombok.experimental.UtilityClass;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Question;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class QuestionUtils {

    public List<Question> getQuestionsForEnLocale() {
        List<Answer> listAnswerForFirstQuestion = new ArrayList<>();
        listAnswerForFirstQuestion.add(new Answer("answer 1", true));
        listAnswerForFirstQuestion.add(new Answer("answer 2", false));
        listAnswerForFirstQuestion.add(new Answer("answer 3", false));

        List<Answer> listAnswerForSecondQuestion = new ArrayList<>();
        listAnswerForSecondQuestion.add(new Answer("answer 1", false));
        listAnswerForSecondQuestion.add(new Answer("answer 2", true));
        listAnswerForSecondQuestion.add(new Answer("answer 3", false));

        List<Question> questions = new ArrayList<>();
        questions.add(new Question("question 1", listAnswerForFirstQuestion));
        questions.add(new Question("question 2", listAnswerForSecondQuestion));

        return questions;
    }

    public List<Question> getQuestionsForRuLocale() {
        List<Answer> listAnswerForFirstQuestion = new ArrayList<>();
        listAnswerForFirstQuestion.add(new Answer("Ответ 1", true));
        listAnswerForFirstQuestion.add(new Answer("Ответ 2", false));
        listAnswerForFirstQuestion.add(new Answer("Ответ 3", false));

        List<Answer> listAnswerForSecondQuestion = new ArrayList<>();
        listAnswerForSecondQuestion.add(new Answer("Ответ 1", false));
        listAnswerForSecondQuestion.add(new Answer("Ответ 2", true));
        listAnswerForSecondQuestion.add(new Answer("Ответ 3", false));

        List<Question> questions = new ArrayList<>();
        questions.add(new Question("Вопрос 1", listAnswerForFirstQuestion));
        questions.add(new Question("Вопрос 2", listAnswerForSecondQuestion));

        return questions;
    }
}
