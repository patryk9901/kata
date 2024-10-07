package org.example;

public class ReceiptLineItem {
    public String productName;
    public double productTotal;
    public int productAmount;

    public ReceiptLineItem(String productName, double productTotal, int productAmount) {
        this.productName = productName;
        this.productTotal = productTotal;
        this.productAmount = productAmount;
    }
}
