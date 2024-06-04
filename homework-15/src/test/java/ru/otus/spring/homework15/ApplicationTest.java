package ru.otus.spring.homework15;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.test.context.ContextConfiguration;

import java.util.Map;

@SpringBootTest(classes = Application.class)
@ContextConfiguration
@IntegrationComponentScan
@Slf4j
public class ApplicationTest {

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @Test
    public void shouldBeCorrectStart() {
        ConfigurableApplicationContext ctx = applicationContext;
        Map<String, MessageChannel> channels = ctx.getBeansOfType(MessageChannel.class);
        log.warn("CHANNELS:");
        int i = 0;
        for (Map.Entry<String, MessageChannel> entry : channels.entrySet()) {
            log.warn("{}. {}/{} -> {}", ++i, entry.getKey(), entry.getValue().getClass().getSimpleName(), entry.getValue());
        }
        log.warn("HANDLERS:");
        i = 0;
        Map<String, MessageHandler> endpoints = ctx.getBeansOfType(MessageHandler.class);
        for (Map.Entry<String, MessageHandler> entry : endpoints.entrySet()) {
            log.warn("{}. {}/{} -> {}", ++i, entry.getKey(), entry.getValue().getClass().getSimpleName(), entry.getValue());
        }
    }
}