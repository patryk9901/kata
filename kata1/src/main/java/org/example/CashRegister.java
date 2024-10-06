package org.example;

import java.util.*;

public class CashRegister {

    HashMap<String,ReceiptLineItem> receiptLineItemHashMap = new HashMap<>();

    public Receipt addProduct(Product product) {
        if(receiptLineItemHashMap.containsKey(product.productName)) {
            ReceiptLineItem receiptLineItem = receiptLineItemHashMap.get(product.productName);
            receiptLineItem.productTotal += product.productPrice;
            receiptLineItem.productAmount++;
        }
        else {
            receiptLineItemHashMap.put(product.productName, new ReceiptLineItem(product.productName, product.productPrice,1));
        }

        Collection<ReceiptLineItem> values = receiptLineItemHashMap.values();
        Iterator<ReceiptLineItem> iterator = values.iterator();
        double total = 0;
        while(iterator.hasNext()){
            ReceiptLineItem next = iterator.next();
            total += next.productTotal;
        }
        return new Receipt(List.copyOf(values), total);
    }
}
