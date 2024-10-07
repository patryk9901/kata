package org.example;

public class LooseProduct implements Product {
    public String productName;
    public double pricePerKg;
    public double weight;

    public LooseProduct(String productName, double pricePerKg,double weight) {
        this.productName = productName;
        this.pricePerKg = pricePerKg;
        this.weight = weight;
    }

    public Double calculatePrice() {
        return weight * pricePerKg;
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