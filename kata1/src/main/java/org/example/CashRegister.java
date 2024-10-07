package org.example;

import java.util.*;

public class CashRegister {

    HashMap<String,ReceiptLineItem> receiptLineItemHashMap = new HashMap<>();
    public Receipt addProduct(Product product , double weight) {

        String productName = product.getProductName();
        double productPrice = product.getProductPrice();
        String productType = product.getProductType();

        if(productType == "packagedProduct") {
            if (receiptLineItemHashMap.containsKey(productName)) {
                ReceiptLineItem receiptLineItem = receiptLineItemHashMap.get(productName);
                receiptLineItem.productTotal += productPrice;
                receiptLineItem.productAmount++;
            } else {
                receiptLineItemHashMap.put(productName, new ReceiptLineItem(productName, productPrice, 1));
            }
        }else if(productType == "looseProduct"){
            if (receiptLineItemHashMap.containsKey(productName)) {
                ReceiptLineItem receiptLineItem = receiptLineItemHashMap.get(productName);
                receiptLineItem.productTotal += productPrice*weight;
            } else {
                receiptLineItemHashMap.put(productName, new ReceiptLineItem(productName, productPrice*weight, 1));
            }
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