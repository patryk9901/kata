package org.example.Promotion;

import org.example.Receipt.ReceiptLineItem;

public interface Promotion {

    public ReceiptLineItem applyPromotion(ReceiptLineItem receiptLineItem);

}
