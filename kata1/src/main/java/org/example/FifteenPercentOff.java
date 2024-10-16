package org.example;

import java.math.BigDecimal;

public class FifteenPercentOff implements Promotion {

        private static final String PROMOTION_NAME = "Fifteen percent off: ";
        private static final BigDecimal discountPercent = BigDecimal.valueOf(0.15);

        public FifteenPercentOff() {
        }

        @Override
        public ReceiptLineItem applyPromotion(ReceiptLineItem receiptLineItem) {
            ReceiptLineItem promotionLineItem = null;
            promotionLineItem = new ReceiptLineItem(PROMOTION_NAME + receiptLineItem.productName, receiptLineItem.productTotal.applyPercentageDiscount(discountPercent), 1);
            return promotionLineItem;
        }
}

