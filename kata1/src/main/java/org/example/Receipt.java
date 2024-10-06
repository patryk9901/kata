package org.example;

import java.util.List;

public class Receipt {

    public double total;

    List<ReceiptLineItem> LineItems;

    public Receipt(List<ReceiptLineItem> lineItems, double total) {
        LineItems = lineItems;
        this.total = total;
    }
}
