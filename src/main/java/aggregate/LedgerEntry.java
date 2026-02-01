package aggregate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "ledger_entries")
@Getter
@NoArgsConstructor
public class LedgerEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private Double amount;

    private String description;

    @Column(name = "idempotency_key")
    private String idempotencyKey;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public LedgerEntry(String type, Double amount, String description, String idempotencyKey) {
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.idempotencyKey = idempotencyKey;
        this.createdAt = LocalDateTime.now();
    }
}