package org.example;

public class ReceiptLineItem {
    public String productName;
    public double productTotal;
    public double productAmount;

    public ReceiptLineItem(String productName, double productTotal, double productAmount) {
        this.productName = productName;
        this.productTotal = productTotal;
        this.productAmount = productAmount;
    }
}
