package org.example;

import java.util.*;

public class CashRegister {

    HashMap<String, ReceiptLineItem> receiptLineItemHashMap = new HashMap<>();

    public Receipt addProduct(Product product) {

        String productName = product.getProductName();

        if (receiptLineItemHashMap.containsKey(productName)) {
            ReceiptLineItem receiptLineItem = receiptLineItemHashMap.get(productName);
            receiptLineItem.productTotal += product.calculatePrice();
            receiptLineItem.productAmount += product.getProductAmount();
        } else {
            receiptLineItemHashMap.put(productName, new ReceiptLineItem(productName, product.calculatePrice(), product.getProductAmount()));
        }

        Collection<ReceiptLineItem> values = receiptLineItemHashMap.values();
        Iterator<ReceiptLineItem> iterator = values.iterator();
        double total = 0;
        while (iterator.hasNext()) {
            ReceiptLineItem next = iterator.next();
            total += next.productTotal;
        }
        return new Receipt(List.copyOf(values), total);
    }
}