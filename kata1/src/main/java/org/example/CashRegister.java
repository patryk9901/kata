package org.example;

import java.math.BigDecimal;
import java.util.*;

public class CashRegister {

    HashMap<String, ReceiptLineItem> receiptLineItemHashMap;
    PromotionProvider promotionProvider;

    public  CashRegister (Map<String, Promotion> productNameToPromotion){
        this.receiptLineItemHashMap = new HashMap<>();
        this.promotionProvider = new PromotionProvider(productNameToPromotion);
    }

    public Receipt addProduct(Product product) {

        String productName = product.getProductName();
        Money productPrice = product.calculatePrice();

        if (receiptLineItemHashMap.containsKey(productName)) {
            ReceiptLineItem receiptLineItem = receiptLineItemHashMap.get(productName);
            receiptLineItem.productTotal = receiptLineItem.productTotal.add(productPrice);
            receiptLineItem.productAmount += product.getProductAmount();
        } else {
            receiptLineItemHashMap.put(productName, new ReceiptLineItem(productName, productPrice, product.getProductAmount()));
        }
        return prepareReceipt(receiptLineItemHashMap);
    }

    public Receipt finishTransaction() {
        for (ReceiptLineItem item : receiptLineItemHashMap.values()) {
            Promotion promotion = promotionProvider.getPromotion(item.productName);
            if(promotion != null){
                ReceiptLineItem updatedItem = promotion.applyPromotion(item);
                receiptLineItemHashMap.put(updatedItem.productName, updatedItem);
            }
        }
        return prepareReceipt(receiptLineItemHashMap);
    }

    private Receipt prepareReceipt(Map<String, ReceiptLineItem> receiptLineItemMap){
        Money total = new Money(BigDecimal.ZERO);

        for (ReceiptLineItem next : receiptLineItemMap.values()) {
            total = total.add(next.productTotal);
        }

        return new Receipt(List.copyOf(receiptLineItemHashMap.values()), total);
    }
}