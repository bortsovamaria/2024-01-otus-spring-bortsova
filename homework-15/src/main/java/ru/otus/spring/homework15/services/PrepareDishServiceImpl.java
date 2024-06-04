package ru.otus.spring.homework15.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework15.models.CookResult;
import ru.otus.spring.homework15.models.PrepareDishResult;

@Service
@Slf4j
public class PrepareDishServiceImpl implements PrepareDishService {
    @Override
    public PrepareDishResult prepare(CookResult result) {
        log.info("Prepare dish {}", result.toString());
        PrepareDishResult prepareDishResult = new PrepareDishResult(result);
        prepareDishResult.setHasReady(true);
        log.info("Dish has ready to serve " + prepareDishResult);
        return prepareDishResult;
    }
}