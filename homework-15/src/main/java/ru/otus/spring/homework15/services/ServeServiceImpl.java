package ru.otus.spring.homework15.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework15.models.FinishedDish;
import ru.otus.spring.homework15.models.PrepareDishResult;

@Service
@Slf4j
public class ServeServiceImpl implements ServedService {
    @Override
    public FinishedDish serve(PrepareDishResult res) {
        log.info("Served {}", res.toString());
        return new FinishedDish(res, true);
    }
}