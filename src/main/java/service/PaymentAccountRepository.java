package service;

import aggregate.PaymentAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PaymentAccountRepository extends JpaRepository<PaymentAccount, Long> {

    Optional<PaymentAccount> findByTaxId(String taxId);

    // CORREÇÃO AQUI:
    // Ensinamos o Spring a buscar diretamente na entidade LedgerEntry
    @Query("SELECT COUNT(l) > 0 FROM LedgerEntry l WHERE l.idempotencyKey = :key")
    boolean existsTransactionByIdempotencyKey(@Param("key") String key);
}