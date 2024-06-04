package ru.otus.spring.homework15.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.MessageChannelSpec;
import org.springframework.integration.dsl.MessageChannels;
import ru.otus.spring.homework15.services.CookService;
import ru.otus.spring.homework15.services.PrepareDishService;
import ru.otus.spring.homework15.services.ServedService;

@Configuration
public class IntegrationConfig {
    @Bean
    public MessageChannelSpec<?, ?> orderChannel() {
        return MessageChannels.queue(10);
    }

    @Bean
    public MessageChannelSpec<?, ?> cookingChannel() {
        return MessageChannels.publishSubscribe();
    }

    @Bean
    public MessageChannelSpec<?, ?> prepareDishChannel() {
        return MessageChannels.publishSubscribe();
    }

    @Bean
    public MessageChannelSpec<?, ?> serveDishChannel() {
        return MessageChannels.publishSubscribe();
    }

    @Bean
    public IntegrationFlow cookFlow(CookService service) {
        return IntegrationFlow.from(orderChannel())
                .split()
                .handle(service, "cooking")
                .aggregate()
                .channel(prepareDishChannel())
                .get();
    }

    @Bean
    public IntegrationFlow prepareFlow(PrepareDishService prepareService, ServedService servedService) {
        return IntegrationFlow.from(prepareDishChannel())
                .split()
                .handle(prepareService, "prepare")
                .handle(servedService, "serve")
                .aggregate()
                .channel(serveDishChannel())
                .get();
    }
}