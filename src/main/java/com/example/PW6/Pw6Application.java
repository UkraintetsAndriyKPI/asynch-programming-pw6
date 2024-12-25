package com.example.PW6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@EnableAsync
@EnableScheduling
@SpringBootApplication
public class Pw6Application {


    public static void main(String[] args) {
        SpringApplication.run(Pw6Application.class, args);

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.schedule(() -> System.out.printf("[%s] executor task 15s delay after start\n",
                LocalDateTime.now()), 15, TimeUnit.SECONDS);

        AsyncTaskService asyncTaskService = new AsyncTaskService();

        asyncTaskService.tryExecuteWithRetry();
    }

}
