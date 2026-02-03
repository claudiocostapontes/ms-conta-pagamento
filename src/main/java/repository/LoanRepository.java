package repository;

import domain.LoanContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<LoanContract, Long> {

    // CORREÇÃO:
    // Errado: findByAccount_Id (procura objeto Account)
    // Certo: findByAccountId (procura o campo Long accountId que criamos acima)
    List<LoanContract> findByAccountId(Long accountId);
}