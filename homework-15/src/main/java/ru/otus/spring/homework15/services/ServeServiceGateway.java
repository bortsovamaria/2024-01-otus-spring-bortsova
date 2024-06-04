package ru.otus.spring.homework15.services;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.spring.homework15.models.FinishedDish;
import ru.otus.spring.homework15.models.PrepareDishResult;

import java.util.Collection;

@MessagingGateway
public interface ServeServiceGateway {
    @Gateway(requestChannel = "prepareDishChannel", replyChannel = "serveDishChannel")
    Collection<FinishedDish> serve(Collection<PrepareDishResult> res);
}