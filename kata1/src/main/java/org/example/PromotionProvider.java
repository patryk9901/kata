package org.example;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class PromotionProvider {
    List<Promotion> activePromotions;

    public PromotionProvider(List<Promotion> activePromotions) {
        this.activePromotions = activePromotions;
    }

    public Promotion getPromotion(ReceiptLineItem receiptLineItem) {
        Money promotionResult = receiptLineItem.productTotal;
        Promotion bestPromotion = null;
       for(Promotion item : activePromotions){
          ReceiptLineItem checkValueOfPromotion = item.applyPromotion(receiptLineItem);
           if(checkValueOfPromotion.productTotal.compareTo(promotionResult) < 0){
               promotionResult = checkValueOfPromotion.productTotal;
               bestPromotion = item;
           }
       }
       return bestPromotion;
    }
}
