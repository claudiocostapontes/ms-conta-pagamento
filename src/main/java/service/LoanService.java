package service;

import domain.ProductType;
import org.springframework.stereotype.Service;

@Service
public class LoanService {

    private final EventPublisher eventPublisher;

    public LoanService(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    // --- NOVO MÉTODO QUE O CONTROLLER ESTÁ PEDINDO ---
    public void processLoan(LoanRequest request) {
        System.out.println("Iniciando processamento de empréstimo via Silo...");

        // Exemplo de integração: valida o tipo do produto vindo do request
        // Supondo que request.getType() retorne uma String como "PERSONAL_LOAN"
        if (request.getType() != null) {
            try {
                ProductType type = ProductType.valueOf(request.getType());
                checkProduct(type);
            } catch (IllegalArgumentException e) {
                System.out.println("Tipo de produto desconhecido: " + request.getType());
            }
        }

        // Simula processamento
        processValue(request.getAmount());

        eventPublisher.publish("LOAN_PROCESSED", "Empréstimo processado para user: " + request.getUserId());
    }

    public void processValue(Double value) {
        if (value == null) return;
        String stringValue = String.valueOf(value);
        System.out.println("Valor validado: " + stringValue);
    }

    public void checkProduct(ProductType type) {
        if (type == ProductType.PERSONAL_LOAN) {
            System.out.println("Regra: Empréstimo Pessoal aprovado.");
        } else {
            System.out.println("Regra: Outro produto (" + type + ")");
        }
    }
}