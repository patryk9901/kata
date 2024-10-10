package org.example;

import org.example.Product.Product;
import org.example.Promotion.Promotion;
import org.example.Promotion.PromotionProvider;
import org.example.Receipt.Receipt;
import org.example.Receipt.ReceiptLineItem;

import java.math.BigDecimal;
import java.util.*;

public class CashRegister {

    LinkedHashMap<String, ReceiptLineItem> receiptLineItemHashMap;
    PromotionProvider promotionProvider;

    public CashRegister(Map<String, Promotion> productNameToPromotion) {
        this.receiptLineItemHashMap = new LinkedHashMap<>();
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
            if (promotion != null) {
                ReceiptLineItem promotionLineItem = promotion.applyPromotion(item);
                receiptLineItemHashMap.put(promotionLineItem.productName, promotionLineItem);
            }
        }
        return prepareReceipt(receiptLineItemHashMap);
    }

    private Receipt prepareReceipt(Map<String, ReceiptLineItem> receiptLineItemMap) {
        Money total = new Money(BigDecimal.ZERO);

        for (ReceiptLineItem next : receiptLineItemMap.values()) {
            total = total.add(next.productTotal);
        }

        return new Receipt(List.copyOf(receiptLineItemHashMap.values()), total);
    }
}