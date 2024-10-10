package org.example.Product;

import org.example.Money;

public interface Product {
    String getProductName();
     Money calculatePrice();
    Double getProductAmount();
}
