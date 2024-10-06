package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CashRegisterTest {

    @Test
    public void shouldAddSingleProduct() {
        //given
        CashRegister register = new CashRegister();
        Product beans = new Product(1, "Beans", 0.65);

        //when
        Receipt result = register.addProduct(beans);

        //then
        assertEquals(0.65, result.total);
        assertEquals(1, result.LineItems.size());
        assertEquals(1, result.LineItems.get(0).productAmount);
        assertEquals(0.65, result.LineItems.get(0).productTotal);
        assertEquals("Beans", result.LineItems.get(0).productName);


    }

    @Test
    public void shouldAddMultipleProducts() {
        //given
        CashRegister register = new CashRegister();

        Product beans = new Product(1, "Beans", 0.65);
        Product bottleOfWater = new Product(2, "BottleOfWater", 0.35);
        Product beans2 = new Product(1, "Beans", 0.65);
        Product bottleOfWater2 = new Product(2, "BottleOfWater", 0.35);
        //when
        Receipt result = register.addProduct(beans);
        Receipt result2 = register.addProduct(bottleOfWater);
        Receipt result3 = register.addProduct(beans2);
        Receipt result4 = register.addProduct(bottleOfWater2);

        //then
        assertEquals(2, result4.total);

        assertEquals(2, result4.LineItems.size());

        assertEquals(2, result4.LineItems.get(0).productAmount);
        assertEquals(1.3, result4.LineItems.get(0).productTotal);
        assertEquals("Beans", result4.LineItems.get(0).productName);

        assertEquals(2, result4.LineItems.get(1).productAmount);
        assertEquals(0.7, result4.LineItems.get(1).productTotal);
        assertEquals("BottleOfWater", result4.LineItems.get(1).productName);

    }

}