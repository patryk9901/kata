package org.example;

import java.math.BigDecimal;

public class ReceiptLineItem {
    public String productName;
    public Money productTotal;
    public double productAmount;

    public ReceiptLineItem(String productName, BigDecimal productTotalAmount, double productAmount) {
        this.productName = productName;
        this.productTotal =  new Money(productTotalAmount);
        this.productAmount = productAmount;
    }
}
