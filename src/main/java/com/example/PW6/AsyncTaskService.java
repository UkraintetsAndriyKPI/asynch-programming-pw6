package com.example.PW6;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Service
public class AsyncTaskService {

    @Async
    public void tryExecuteWithRetry() {
        int attempts = 0;
        boolean success = false;

        while (attempts < 3 && !success) {
            attempts++;
            try {
                System.out.println(LocalDateTime.now() + " | Спроба " + attempts + " виконується...");
                TimeUnit.SECONDS.sleep(5);

                if (Math.random() * 10 > 5) {
                    success = true;
                    System.out.println("Метод успішно виконаний.");
                    return;
                } else {
                    System.out.println("Метод не вдався.");
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Помилка при виконанні: " + e.getMessage());
            }
        }

        System.out.println("ПОМИЛКА: Не вдалося виконати метод після 3 спроб.");
    }

    @Async
    @Scheduled(initialDelay = 15, fixedRate = Long.MAX_VALUE, timeUnit = TimeUnit.SECONDS)
    public void executeAfter15SecondsSWING() {
        System.out.printf("[%s] 15 секунд від запуску ЗАДАЧА ВИКОНАНА\n", LocalDateTime.now());
    }
}
