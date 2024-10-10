package org.example.Promotion;

import java.util.Map;

public class PromotionProvider {
    Map<String, Promotion> productNameToPromotion;

    public PromotionProvider(Map<String, Promotion> productNameToPromotion) {
        this.productNameToPromotion = productNameToPromotion;
    }

    public Promotion getPromotion(String productName) {
        return productNameToPromotion.get(productName);
    }
}
