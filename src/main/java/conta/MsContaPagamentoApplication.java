package conta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
// 1. ComponentScan: Garante que o Spring ache seus Controllers e Services
@ComponentScan(basePackages = {
        "conta",
        "service",
        "controller",
        "config",
        "aggregate",
        "repository",
        "domain" // Adicionado para garantir
})
// 2. EntityScan: CRÍTICO! Adicionei "domain" aqui.
// Sem isso, o erro "Not a managed type: LoanContract" vai voltar.
@EntityScan(basePackages = {
        "aggregate", // Onde está PaymentAccount
        "domain"     // Onde está LoanContract (A CORREÇÃO ESTÁ AQUI)
})
// 3. EnableJpaRepositories: Onde estão as interfaces que estendem JpaRepository
@EnableJpaRepositories(basePackages = {
        "repository",
        "service" // Mantive caso você tenha algum repositório legado aqui
})
public class MsContaPagamentoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsContaPagamentoApplication.class, args);
    }
}