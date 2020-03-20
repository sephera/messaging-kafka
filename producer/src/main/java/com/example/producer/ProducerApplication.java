package com.example.producer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

@Log4j2
@EnableBinding(Source.class)
@SpringBootApplication
public class ProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }


    @Bean
    public ApplicationRunner onPublish(Source source) {
        return args -> {
            source.output().send(MessageBuilder.withPayload(new PersonEvent("Arya")).build());
            log.info("send event completed!");
        };
    }
}

@Data
@AllArgsConstructor
class PersonEvent {
    private String name;
}
