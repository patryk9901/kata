package org.example.Promotion;

import org.example.Receipt.ReceiptLineItem;

public class EightPlusEight implements Promotion {

    private static final String PROMOTION_NAME = "Eight plus eight: ";

    public EightPlusEight() {
    }

    @Override
    public ReceiptLineItem applyPromotion(ReceiptLineItem receiptLineItem) {
        ReceiptLineItem promotionLineItem = null;
        if (receiptLineItem.productAmount % 8 == 0 && receiptLineItem.productAmount >= 8) {
            promotionLineItem = new ReceiptLineItem(PROMOTION_NAME + receiptLineItem.productName, receiptLineItem.productTotal.negativeHalf(), 1);
        }
        return promotionLineItem;
    }
}
