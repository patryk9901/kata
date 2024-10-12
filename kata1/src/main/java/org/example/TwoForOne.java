package org.example;

public class TwoForOne implements Promotion {

    private static final String PROMOTION_NAME = "Two for one: ";

    public TwoForOne() {
    }

    @Override
    public ReceiptLineItem applyPromotion(ReceiptLineItem receiptLineItem) {
        ReceiptLineItem promotionLineItem = null;
        if (receiptLineItem.productAmount % 2 == 0) {
            promotionLineItem = new ReceiptLineItem(PROMOTION_NAME + receiptLineItem.productName, receiptLineItem.productTotal.negativeHalf(), 1);
        }
        return promotionLineItem;
    }
}
