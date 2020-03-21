package com.example.streamconsumer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;


@EnableBinding(Sink.class)
@Log4j2
@SpringBootApplication
public class StreamConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StreamConsumerApplication.class, args);
    }

    @StreamListener(Sink.INPUT)
    public void consumeEmployeeDetails(PersonEvent personEvent) {
        log.info("Let's process employee details: {}", personEvent);
    }
}


@Data
@AllArgsConstructor
class PersonEvent {
    private String name;
}