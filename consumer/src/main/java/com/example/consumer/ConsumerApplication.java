package com.example.consumer;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;


@Log4j2
@EnableBinding(Sink.class)
@SpringBootApplication
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

    @StreamListener(Sink.INPUT)
    public void onSubscribe(PersonEvent personEvent) {
        log.info("Receive: {}", personEvent);
    }
}

@Data
class PersonEvent {
    private String name;
}
