package com.example.streamprocessor;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.binder.kafka.streams.annotations.KafkaStreamsProcessor;
import org.springframework.messaging.handler.annotation.SendTo;

@EnableBinding(KafkaStreamsProcessor.class)
@SpringBootApplication
public class StreamProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(StreamProcessorApplication.class, args);
    }


    @StreamListener("input")
    @SendTo("output")
    public KStream<?, PersonEvent> process(KStream<?, String> name) {
        return name
                .map((K, V) -> new KeyValue<>(K, new PersonEvent(V)));
    }

}


@Data
@AllArgsConstructor
class PersonEvent {
    private String name;
}