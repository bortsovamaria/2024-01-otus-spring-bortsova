package ru.otus.spring.homework15.services;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.spring.homework15.models.CookResult;
import ru.otus.spring.homework15.models.PrepareDishResult;

import java.util.Collection;

@MessagingGateway
public interface PrepareServiceGateway {
    @Gateway(requestChannel = "cookingChannel", replyChannel = "prepareDishChannel")
    Collection<PrepareDishResult> prepare(Collection<CookResult> res);
}