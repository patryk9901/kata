package org.example;

import java.math.BigDecimal;
import java.util.*;

public class CashRegister {

    HashMap<String, ReceiptLineItem> receiptLineItemHashMap = new HashMap<>();

    public Receipt addProduct(Product product) {

        String productName = product.getProductName();
        Money productPrice = product.calculatePrice();

        if (receiptLineItemHashMap.containsKey(productName)) {
            ReceiptLineItem receiptLineItem = receiptLineItemHashMap.get(productName);
            receiptLineItem.productTotal = receiptLineItem.productTotal.add(productPrice);
            receiptLineItem.productAmount += product.getProductAmount();
        } else {
            receiptLineItemHashMap.put(productName, new ReceiptLineItem(productName, productPrice.getAmount(), product.getProductAmount()));
        }

        Money totalMoney = new Money(BigDecimal.ZERO);

        for (ReceiptLineItem next : receiptLineItemHashMap.values()) {
            totalMoney = totalMoney.add(next.productTotal);
        }

        return new Receipt(List.copyOf(receiptLineItemHashMap.values()), totalMoney);
    }
}