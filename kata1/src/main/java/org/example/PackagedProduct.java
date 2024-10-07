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
    public String getProductType() {
        return "packagedProduct";
    }

    @Override
    public Double getProductPrice() {
        return productPrice;
    }


}