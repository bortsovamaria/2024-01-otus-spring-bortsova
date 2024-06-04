package ru.otus.spring.homework15.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework15.models.Order;
import ru.otus.spring.homework15.models.OrderType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final CookServiceGateway cookServiceGateway;

    @Override
    public void startGenerateOrdersLoop() {
        ForkJoinPool pool = ForkJoinPool.commonPool();
        for (int i = 0; i < 10; i++) {
            int num = i + 1;
            pool.execute(() -> {
                Collection<Order> items = generateOrderItems();
                log.info("{}, New orderItems: {}", num, items.stream()
                        .map(Order::toString)
                        .collect(Collectors.joining(", ")));
                cookServiceGateway.cooking(items);
            });
            delay();
        }
    }

    private static Collection<Order> generateOrderItems() {
        Random random = new Random();
        List<Order> items = new ArrayList<>();
        for (int i = 0; i < random.nextInt(1, 10); i++) {
            items.add(generateOrderItem());
        }
        return items;
    }

    private static Order generateOrderItem() {
        Random random = new Random();
        OrderType[] orderTypes = OrderType.values();

        return new Order(orderTypes[random.nextInt(0, orderTypes.length)]
                .getTypeName(),
                random.nextInt(1, 20),
                random.nextInt(1, 20)
        );
    }

    private void delay() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}