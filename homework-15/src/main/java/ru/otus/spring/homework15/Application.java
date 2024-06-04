package ru.otus.spring.homework15;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.spring.homework15.services.OrderService;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);

        OrderService orderService = ctx.getBean(OrderService.class);
        orderService.startGenerateOrdersLoop();
    }
}
