package org.example;

public class PackagedProduct implements Product {
    public String productName;
    public double productPrice;

    public PackagedProduct(String productName, double productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
    }

    @Override
    public String getProductName() {
        return productName;
    }

    @Override
    public Double calculatePrice() {
        return productPrice;
    }

    @Override
    public Double getProductAmount() {
        return 1.0;
    }
}