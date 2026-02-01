package service;

import aggregate.PaymentAccount;
import aggregate.Money; // Agora vai pegar o Money certo
import aggregate.InsufficientFundsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountManagementService {

    private final PaymentAccountRepository repository;
    private final EventPublisher eventPublisher;

    @Transactional
    public PaymentAccount openAccount(String taxId) {
        if (repository.findByTaxId(taxId).isPresent()) {
            throw new IllegalArgumentException("Customer already has an account");
        }

        PaymentAccount newAccount = new PaymentAccount();
        // newAccount.setTaxId(taxId); // Descomente se tiver o setter
        PaymentAccount saved = repository.save(newAccount);

        eventPublisher.publishAccountCreated();
        return saved;
    }

    @Transactional
    public void processTransaction(String taxId, Double amount, String type, String idempotencyKey) throws InsufficientFundsException {
        if (repository.existsTransactionByIdempotencyKey(idempotencyKey)) {
            return;
        }

        PaymentAccount account = repository.findByTaxId(taxId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        Money money = new Money(amount);

        if ("DEBIT".equalsIgnoreCase(type)) {
            account.debit(money, "Debit via API", idempotencyKey);
        } else {
            account.credit(money);
        }

        repository.save(account);
        eventPublisher.publishBalanceUpdated();
    }
}