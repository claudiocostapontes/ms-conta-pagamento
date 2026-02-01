package aggregate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "payment_accounts")
@NoArgsConstructor(force = true)
@Getter
public class PaymentAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "id_value"))
    private AccountId accountId;

    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "balance"))
    private Money balance;

    private String taxId;

    private boolean active = true;

    @Version
    private Long version;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private List<LedgerEntry> ledger = new ArrayList<>();

    /**
     * Realiza um débito na conta.
     * @param amount Valor a ser debitado.
     * @param description Descrição da transação.
     * @param idempotencyKey Chave para evitar duplicidade.
     * @throws InsufficientFundsException Caso o saldo seja insuficiente.
     */
    public void debit(Money amount, String description, String idempotencyKey) throws InsufficientFundsException {
        if (!this.active) {
            throw new IllegalStateException("Account is inactive");
        }

        // Lógica de negócio usando o objeto de valor Money
        if (this.balance.amount() < amount.amount())
            throw new InsufficientFundsException("Insufficient funds for debit");

        // Atualiza o saldo criando um novo objeto Money (Imutabilidade)
        this.balance = new Money(this.balance.amount());

        // Registra no extrato (Ledger)
        this.ledger.add(new LedgerEntry("DEBIT", amount.amount(), description, idempotencyKey));
    }

    /**
     * Realiza um crédito na conta.
     * @param amount Valor a ser creditado.
     */
    public void credit(Money amount) {
        if (!this.active) {
            throw new IllegalStateException("Account is inactive");
        }

        // Atualiza o saldo somando os valores
        this.balance = new Money(this.balance.amount() + amount.amount());

        // Registra no extrato
        this.ledger.add(new LedgerEntry("CREDIT", amount.amount(), "Credit deposit", null));
    }
}