package repository;

import aggregate.PaymentAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PaymentAccountRepository extends JpaRepository<PaymentAccount, Long> {

    // Método necessário para o AccountManagementService funcionar
    Optional<PaymentAccount> findByTaxId(String taxId);

    // Se você usar a verificação de idempotência no futuro, descomente abaixo:
    // boolean existsTransactionByIdempotencyKey(String idempotencyKey);
}
