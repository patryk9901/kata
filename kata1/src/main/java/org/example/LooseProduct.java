package org.example;

import java.math.BigDecimal;
import java.util.Currency;

public class LooseProduct implements Product {
    public String productName;
    public Money pricePerKg;
    public double weight;

    public LooseProduct(String productName, BigDecimal priceAmount, double weight) {
        this.productName = productName;
        this.pricePerKg = new Money(priceAmount, Currency.getInstance("PLN"));
        this.weight = weight;
    }

    public Money calculatePrice() {
        return pricePerKg.multiply(weight);
    }

    @Override
    public Double getProductAmount() {
        return weight;
    }

    @Override
    public String getProductName() {
        return productName;
    }
}