package service;
import org.springframework.stereotype.Component;

@Component
public class EventPublisherImpi implements EventPublisher {
    @Override
    public void publishAccountCreated() { System.out.println("Conta criada!"); }

    @Override
    public void publishBalanceUpdated() { System.out.println("Saldo atualizado!"); }
}