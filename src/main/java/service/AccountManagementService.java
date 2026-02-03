package service;

import aggregate.InsufficientFundsException;
import aggregate.Money;
import aggregate.PaymentAccount;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.PaymentAccountRepository;

@Service
public class AccountManagementService {

    private final PaymentAccountRepository repository;
    private final EventPublisher eventPublisher;

    // Injeção de dependência via construtor
    public AccountManagementService(PaymentAccountRepository repository, EventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public PaymentAccount openAccount(String taxId) {
        // Lógica simplificada para exemplo
        PaymentAccount account = new PaymentAccount();
        // account.setTaxId(taxId); // Supondo que exista esse método

        PaymentAccount saved = repository.save(account);

        // CORREÇÃO AQUI: Usando o método genérico publish
        eventPublisher.publish("ACCOUNT_CREATED", "Conta criada para o TaxID: " + taxId);

        return saved;
    }

    @Transactional
    public void processTransaction(String taxId, Double amount, String type, String idempotencyKey) throws InsufficientFundsException {
        // Se a transação já existe, ignora (Idempotência)
        // if (repository.existsTransactionByIdempotencyKey(idempotencyKey)) return;

        PaymentAccount account = repository.findByTaxId(taxId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        Money money = new Money(amount);

        if ("DEBIT".equalsIgnoreCase(type)) {
            account.debit(money, "Debit via API", idempotencyKey);
        } else {
            account.credit(money);
        }

        repository.save(account);

        // CORREÇÃO AQUI: Usando o método genérico publish
        eventPublisher.publish("BALANCE_UPDATED", "Saldo atualizado para a conta " + taxId);
    }
}