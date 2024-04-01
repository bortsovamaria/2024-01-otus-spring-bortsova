package ru.otus.spring.domain;

import java.util.ArrayList;
import java.util.List;

public class TestResult {

    private final Student student;

    private final List<Question> answeredQuestions;

    private int rightAnswersCount;

    public TestResult(Student student) {
        this.student = student;
        this.answeredQuestions = new ArrayList<>();
    }

    public void applyAnswer(Question question, boolean isRightAnswered) {
        answeredQuestions.add(question);
        if (isRightAnswered) {
            rightAnswersCount++;
        }
    }

    public Student getStudent() {
        return this.student;
    }

    public List<Question> getAnsweredQuestions() {
        return this.answeredQuestions;
    }

    public int getRightAnswersCount() {
        return this.rightAnswersCount;
    }

    public void setRightAnswersCount(int rightAnswersCount) {
        this.rightAnswersCount = rightAnswersCount;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof TestResult)) return false;
        final TestResult other = (TestResult) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$student = this.getStudent();
        final Object other$student = other.getStudent();
        if (this$student == null ? other$student != null : !this$student.equals(other$student)) return false;
        final Object this$answeredQuestions = this.getAnsweredQuestions();
        final Object other$answeredQuestions = other.getAnsweredQuestions();
        if (this$answeredQuestions == null ? other$answeredQuestions != null : !this$answeredQuestions.equals(other$answeredQuestions))
            return false;
        if (this.getRightAnswersCount() != other.getRightAnswersCount()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof TestResult;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $student = this.getStudent();
        result = result * PRIME + ($student == null ? 43 : $student.hashCode());
        final Object $answeredQuestions = this.getAnsweredQuestions();
        result = result * PRIME + ($answeredQuestions == null ? 43 : $answeredQuestions.hashCode());
        result = result * PRIME + this.getRightAnswersCount();
        return result;
    }

    public String toString() {
        return "TestResult(student=" + this.getStudent() + ", answeredQuestions=" + this.getAnsweredQuestions() + ", rightAnswersCount=" + this.getRightAnswersCount() + ")";
    }
}