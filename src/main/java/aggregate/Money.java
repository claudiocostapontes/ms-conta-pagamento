package aggregate;

import java.math.BigDecimal;

public record Money(Double amount) {

    public Money {
        if (amount == null) amount = 0.0;
    }

    public boolean isNegative() {
        return amount < 0;
    }

    public Money subtract(Money other) {
        return new Money(this.amount - other.amount());
    }

    public Money add(Money other) {
        return new Money(this.amount + other.amount());
    }
}