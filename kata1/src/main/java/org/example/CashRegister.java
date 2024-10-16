package org.example;

import java.math.BigDecimal;
import java.util.*;

public class CashRegister {

    LinkedHashMap<String, ReceiptLineItem> receiptLineItemHashMap;
    PromotionProvider promotionProvider;
    private Currency defaultCurrency = Currency.getInstance("PLN");
    private ClientNBP clientNBP;

    public CashRegister(Map<String, Promotion> productNameToPromotion) {
        this.receiptLineItemHashMap = new LinkedHashMap<>();
        this.promotionProvider = new PromotionProvider(productNameToPromotion);
        this.clientNBP = new ClientNBP();
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
        return prepareReceipt(receiptLineItemHashMap, defaultCurrency);
    }

    public Receipt finishTransaction(Currency currency) {
        for (ReceiptLineItem item : receiptLineItemHashMap.values()) {
            Promotion promotion = promotionProvider.getPromotion(item.productName);//TODO ITEM
            if (promotion != null) {
                ReceiptLineItem promotionLineItem = promotion.applyPromotion(item);
                receiptLineItemHashMap.put(promotionLineItem.productName,
                        new ReceiptLineItem(promotionLineItem.productName,
                                new Money(promotionLineItem.productTotal.getAmount(), defaultCurrency),
                                promotionLineItem.productAmount));
            }
        }
        return prepareReceipt(receiptLineItemHashMap, currency);
    }

    private Receipt prepareReceipt(Map<String, ReceiptLineItem> receiptLineItemMap, Currency currency) {
        Money total = new Money(BigDecimal.ZERO, defaultCurrency);

        for (ReceiptLineItem next : receiptLineItemMap.values()) {
            total = total.add(next.productTotal);
        }

        if (!currency.equals(defaultCurrency)) {
            BigDecimal convertedTotalAmount = clientNBP.convertFromPLN(total.getAmount(), currency);
            total = new Money(convertedTotalAmount, currency);
        }

        return new Receipt(List.copyOf(receiptLineItemMap.values()), total);
    }
}