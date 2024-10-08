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



//    private BigDecimal amount;
//    private Currency currency;
//
//    public Money(BigDecimal amount, Currency currency) {
//        if (amount == null || currency == null) {
//            throw new IllegalArgumentException("Amount and currency cannot be null");
//        }
//        this.amount = amount.setScale(2, RoundingMode.HALF_UP);
//        this.currency = currency;
//    }
//
//    public BigDecimal getAmount() {
//        return amount;
//    }
//
//    public Currency getCurrency() {
//        return currency;
//    }
//    public Money add(Money other) {
//        if (!this.currency.equals(other.currency)) {
//            throw new IllegalArgumentException("Cannot add money with different currencies");
//        }
//        return new Money(this.amount.add(other.amount), this.currency);
//    }
//
//    public Money subtract(Money other) {
//        if (!this.currency.equals(other.currency)) {
//            throw new IllegalArgumentException("Cannot subtract money with different currencies");
//        }
//        return new Money(this.amount.subtract(other.amount), this.currency);
//    }
//
//    @Override
//    public String toString() {
//        return amount + " " + currency.getSymbol();
//    }
//
}