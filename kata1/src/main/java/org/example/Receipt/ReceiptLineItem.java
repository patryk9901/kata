package org.example.Receipt;

import org.example.Money;

public class ReceiptLineItem {
    public String productName;
    public Money productTotal;
    public double productAmount;

    public ReceiptLineItem(String productName, Money productTotalAmount, double productAmount) {
        this.productName = productName;
        this.productTotal =  productTotalAmount;
        this.productAmount = productAmount;
    }
}
