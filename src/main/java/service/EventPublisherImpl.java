package service;

import org.springframework.stereotype.Service;

@Service
public class EventPublisherImpl  implements EventPublisher {

    @Override
    public void publish(String topic, String message) {
        System.out.println("Enviando evento para o tópico " + topic + ": " + message);
    }

}

