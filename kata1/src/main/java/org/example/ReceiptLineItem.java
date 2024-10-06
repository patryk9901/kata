package org.example;

public class ReceiptLineItem {
    public String productName;
    public double productTotal;
    public int productAmount;
    public int productId;

    public ReceiptLineItem(String productName, double productTotal, int productAmount, int productId) {
        this.productName = productName;
        this.productTotal = productTotal;
        this.productAmount = productAmount;
        this.productId = productId;
    }


}
