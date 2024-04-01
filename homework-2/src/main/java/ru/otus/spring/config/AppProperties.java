package ru.otus.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppProperties implements TestConfig, TestFileNameProvider {

    @Value("${test.rightAnswersCountToPass}")
    private int rightAnswersCountToPass;

    @Value("${test.fileName}")
    private String testFileName;

    public AppProperties() {
    }

    public int getRightAnswersCountToPass() {
        return this.rightAnswersCountToPass;
    }

    public String getTestFileName() {
        return this.testFileName;
    }

    public void setRightAnswersCountToPass(int rightAnswersCountToPass) {
        this.rightAnswersCountToPass = rightAnswersCountToPass;
    }

    public void setTestFileName(String testFileName) {
        this.testFileName = testFileName;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof AppProperties)) return false;
        final AppProperties other = (AppProperties) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getRightAnswersCountToPass() != other.getRightAnswersCountToPass()) return false;
        final Object this$testFileName = this.getTestFileName();
        final Object other$testFileName = other.getTestFileName();
        if (this$testFileName == null ? other$testFileName != null : !this$testFileName.equals(other$testFileName))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof AppProperties;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getRightAnswersCountToPass();
        final Object $testFileName = this.getTestFileName();
        result = result * PRIME + ($testFileName == null ? 43 : $testFileName.hashCode());
        return result;
    }

    public String toString() {
        return "AppProperties(rightAnswersCountToPass=" + this.getRightAnswersCountToPass() + ", testFileName=" + this.getTestFileName() + ")";
    }
}