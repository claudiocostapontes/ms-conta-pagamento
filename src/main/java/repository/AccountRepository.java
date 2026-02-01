package repository;

import aggregate.PaymentAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository<AccountId> extends JpaRepository<PaymentAccount, AccountId> {
    Optional<PaymentAccount> findByTaxId(String taxId);
    
    // Método para verificação de Idempotência no Ledger
    @Query("SELECT count(l) > 0 FROM LedgerEntry l WHERE l.idempotencyKey = :key")
    boolean existsTransactionByIdempotencyKey(String key);
}