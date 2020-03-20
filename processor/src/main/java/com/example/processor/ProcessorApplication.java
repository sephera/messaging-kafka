package com.example.processor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.SendTo;


@Log4j2
@EnableBinding(Processor.class)
@SpringBootApplication
public class ProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProcessorApplication.class, args);
    }


    @StreamListener(Processor.INPUT)
    @SendTo(Processor.OUTPUT)
    public PersonEvent receiveAndSend(String name) {
        log.info("receive event {}", name);
        return new PersonEvent(name);
    }

}

@Data
@AllArgsConstructor
class PersonEvent {
    private String name;
}
