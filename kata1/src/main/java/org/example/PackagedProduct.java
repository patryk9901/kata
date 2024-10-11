package org.example;

import java.math.BigDecimal;
import java.util.Currency;

public class PackagedProduct implements Product {
    public String productName;
    public Money productPrice;

    public PackagedProduct(String productName, BigDecimal priceAmount) {
        this.productName = productName;
        this.productPrice = new Money(priceAmount, Currency.getInstance("PLN"));
    }

    @Override
    public String getProductName() {
        return productName;
    }

    @Override
    public Money calculatePrice() {
        return productPrice;
    }

    @Override
    public Double getProductAmount() {
        return 1.0;
    }
}