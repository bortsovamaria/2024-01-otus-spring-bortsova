package ru.otus.homework;


import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.homework.service.TestRunnerService;

public class Application {
    public static void main(String[] args) {
        var context = new ClassPathXmlApplicationContext("/spring-context.xml");
        TestRunnerService service = context.getBean(TestRunnerService.class);
        service.run();
    }
}