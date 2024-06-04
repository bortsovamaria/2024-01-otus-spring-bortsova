package ru.otus.spring.homework15.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework15.models.CookResult;
import ru.otus.spring.homework15.models.Order;
import ru.otus.spring.homework15.models.OrderType;

@Service
@Slf4j
public class CookServiceImpl implements CookService {
    @Override
    public CookResult cooking(Order order) {
        log.info("Start cooking {}", order.toString());
        return new CookResult(OrderType.getFromTypeName(order.getType()),
                order.getTableNum(),
                order.getCount());
    }
}