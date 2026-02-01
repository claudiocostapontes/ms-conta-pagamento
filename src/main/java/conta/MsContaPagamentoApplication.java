package conta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
// 1. ComponentScan: Ensina o Spring a olhar todas as suas pastas "irmãs"
// ATENÇÃO: Verifique se sua pasta de controller chama "controller" ou "controller" (com um L só) e ajuste aqui se necessário.
@ComponentScan(basePackages = {"conta", "service", "controller", "config", "aggregate", "repository"})
// 2. EntityScan: Aponta para a pasta onde estão suas Entidades (PaymentAccount, LedgerEntry)
@EntityScan(basePackages = {"aggregate"})
// 3. EnableJpaRepositories: Aponta para onde estão seus Repositórios
@EnableJpaRepositories(basePackages = {"repository", "service"})
public class MsContaPagamentoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsContaPagamentoApplication.class, args);
    }
}