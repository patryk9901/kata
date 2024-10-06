package org.example;

import java.util.ArrayList;
import java.util.List;

public class CashRegister {

    List<Double> products = new ArrayList<>();
    //ReceiptLineItem receiptLineItem = new ReceiptLineItem("Beans",0.65,1);
    List<ReceiptLineItem> receiptLineItems = new ArrayList<>();

    public Receipt addProduct(Product product) {
        double totalOfOneProduct = 0;
        int productAmount = 0;
        double total = 0;
        boolean productExists = false;

        products.add(product.productPrice);

        for (ReceiptLineItem item : receiptLineItems) {
            if (item.productId == product.productId) {

                item.productTotal += product.productPrice;
                item.productAmount++;
                productExists = true;
                break;
            }
        }
        if (!productExists) {

            totalOfOneProduct += product.productPrice;
            productAmount = 1;
            ReceiptLineItem receiptLineItem = new ReceiptLineItem(product.productName, totalOfOneProduct, productAmount, product.productId);
            receiptLineItems.add(receiptLineItem);
        }

        for (int i = 0; i < products.size(); i++) {
            total += products.get(i);
        }
        return new Receipt(receiptLineItems, total);
    }

}
