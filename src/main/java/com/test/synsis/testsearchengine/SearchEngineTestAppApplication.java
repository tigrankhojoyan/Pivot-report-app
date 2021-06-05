package com.test.synsis.testsearchengine;

import com.test.synsis.testsearchengine.service.TestService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@AllArgsConstructor
@SpringBootApplication
@EnableTransactionManagement
@Slf4j
public class SearchEngineTestAppApplication {

//    private final TestService testService;

    public static void main(String[] args) {
        SpringApplication.run(SearchEngineTestAppApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
//        log.info("Init test data");
//        testService.initTestData();
//        log.info("Test data has been initialized successfully");
    }

}
