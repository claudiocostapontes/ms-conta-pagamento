package service;

public interface EventPublisher {

    void publishAccountCreated();
    void publishBalanceUpdated();
}
