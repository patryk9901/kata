package org.example;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CashRegisterTest {

    @Test
    public void shouldAddSingleProduct() {
        //given
        CashRegister register = new CashRegister();
        PackagedProduct beans = new PackagedProduct("Beans", BigDecimal.valueOf(0.65));

        //when
        Receipt result = register.addProduct(beans);

        //then
        assertEquals(new Money(BigDecimal.valueOf(0.65)), result.total);
        assertEquals(1, result.LineItems.size());
        assertEquals(1, result.LineItems.get(0).productAmount);
        assertEquals(new Money(BigDecimal.valueOf(0.65)), result.LineItems.get(0).productTotal);
        assertEquals("Beans", result.LineItems.get(0).productName);
    }

    @Test
    public void shouldAddMultipleProducts() {
        //given
        CashRegister register = new CashRegister();

        PackagedProduct beans = new PackagedProduct("Beans", BigDecimal.valueOf(0.65));
        PackagedProduct bottleOfWater = new PackagedProduct("BottleOfWater", BigDecimal.valueOf(0.35));
        PackagedProduct beans2 = new PackagedProduct("Beans", BigDecimal.valueOf(0.65));
        PackagedProduct bottleOfWater2 = new PackagedProduct("BottleOfWater", BigDecimal.valueOf(0.35));
        //when
        Receipt result = register.addProduct(beans);
        Receipt result2 = register.addProduct(bottleOfWater);
        Receipt result3 = register.addProduct(beans2);
        Receipt result4 = register.addProduct(bottleOfWater2);

        //then
        assertEquals(new Money(BigDecimal.valueOf(2)), result4.total);
        assertEquals(2, result4.LineItems.size());

        assertEquals(2, result4.LineItems.get(0).productAmount);
        assertEquals(new Money(BigDecimal.valueOf(1.30)), result4.LineItems.get(0).productTotal);
        assertEquals("Beans", result4.LineItems.get(0).productName);

        assertEquals(2, result4.LineItems.get(1).productAmount);
        assertEquals(new Money(BigDecimal.valueOf(0.70)), result4.LineItems.get(1).productTotal);
        assertEquals("BottleOfWater", result4.LineItems.get(1).productName);
    }

    @Test
    public void shouldAddLooseProduct() {
        //given
        CashRegister register = new CashRegister();
        LooseProduct sausage = new LooseProduct("Slaska", BigDecimal.valueOf(15.50), 0.833);
        //when
        Receipt result = register.addProduct(sausage);


        //then
        assertEquals(new Money(BigDecimal.valueOf(12.91)), result.total);
        assertEquals(1, result.LineItems.size());

        assertEquals(0.833, result.LineItems.get(0).productAmount);
        assertEquals(new Money(BigDecimal.valueOf(12.91)), result.LineItems.get(0).productTotal);
        assertEquals("Slaska", result.LineItems.get(0).productName);
    }

    @Test
    public void shouldAddMultipleLooseProduct() {
        //given
        CashRegister register = new CashRegister();

        LooseProduct sausage = new LooseProduct("Slaska", BigDecimal.valueOf(2), 2);
        LooseProduct ham = new LooseProduct("Krakowska", BigDecimal.valueOf(1), 2);
        LooseProduct ham2 = new LooseProduct("Krakowska", BigDecimal.valueOf(1), 2);
        LooseProduct sausage2 = new LooseProduct("Slaska", BigDecimal.valueOf(2), 2);

        //when
        register.addProduct(sausage);
        register.addProduct(ham);
        register.addProduct(ham2);
        Receipt result = register.addProduct(sausage2);


        //then
        assertEquals(new Money(BigDecimal.valueOf(12)), result.total);
        assertEquals(2, result.LineItems.size());

        assertEquals(4, result.LineItems.get(0).productAmount);
        assertEquals(new Money(BigDecimal.valueOf(8)), result.LineItems.get(0).productTotal);
        assertEquals("Slaska", result.LineItems.get(0).productName);

        assertEquals(4, result.LineItems.get(1).productAmount);
        assertEquals(new Money(BigDecimal.valueOf(4)), result.LineItems.get(1).productTotal);
        assertEquals("Krakowska", result.LineItems.get(1).productName);
    }
}