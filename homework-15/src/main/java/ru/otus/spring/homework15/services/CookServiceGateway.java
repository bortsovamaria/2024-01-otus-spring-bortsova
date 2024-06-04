package ru.otus.spring.homework15.services;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.spring.homework15.models.CookResult;
import ru.otus.spring.homework15.models.Order;

import java.util.Collection;

@MessagingGateway
public interface CookServiceGateway {
    @Gateway(requestChannel = "orderChannel", replyChannel = "cookingChannel")
    Collection<CookResult> cooking(Collection<Order> orders);
}