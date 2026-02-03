package service;

public interface EventPublisher {
    void publish(String topic, String message);
}