package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

public class Money {
    private BigDecimal amount;

    public Money(BigDecimal amount) {
        if (amount == null) {
            throw new IllegalArgumentException("Amount cannot be null");
        }
        this.amount = amount.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Money add(Money other) {
        return new Money(this.amount.add(other.amount));
    }

    public Money subtract(Money other) {
        return new Money(this.amount.subtract(other.amount));
    }

    public Money negativeHalf() {
        return new Money(amount.negate().divide(BigDecimal.valueOf(2)));
    }

    @Override
    public String toString() {
        return amount.toString();
    }

    public Money multiply(double factor) {
        return new Money(this.amount.multiply(BigDecimal.valueOf(factor)));
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Money)) return false;
        Money other = (Money) obj;
        return this.amount.compareTo(other.amount) == 0;
    }

    @Override
    public int hashCode() {
        return amount.hashCode();
    }
}