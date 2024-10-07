package org.example;

public class LooseProduct implements Product {
    public String productName;
    public double pricePerKg;

    public LooseProduct(String productName, double pricePerKg) {
        this.productName = productName;
        this.pricePerKg = pricePerKg;
    }

    public double calculatePrice(double weight) {
        return weight * pricePerKg;
    }

    @Override
    public String getProductName() {
        return productName;
    }

    @Override
    public String getProductType() {
        return "looseProduct";
    }

    @Override
    public Double getProductPrice() {
        return pricePerKg;
    }
}