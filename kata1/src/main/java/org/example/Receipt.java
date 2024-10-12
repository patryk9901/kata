package org.example;

import java.util.List;

public class Receipt {

    public Money total;

   public List<ReceiptLineItem> LineItems;

    public Receipt(List<ReceiptLineItem> lineItems, Money total) {
        LineItems = lineItems;
        this.total = total;
    }
}
