package org.example;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CashRegisterTest {

    @Test
    public void shouldAddSingleProduct() {
        //given
        CashRegister register = new CashRegister(Map.of());
        PackagedProduct beans = new PackagedProduct("Beans", BigDecimal.valueOf(0.65));

        //when
        register.addProduct(beans);
        Receipt result = register.finishTransaction();

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
        CashRegister register = new CashRegister(Map.of());

        PackagedProduct beans = new PackagedProduct("Beans", BigDecimal.valueOf(0.65));
        PackagedProduct bottleOfWater = new PackagedProduct("BottleOfWater", BigDecimal.valueOf(0.35));
        PackagedProduct beans2 = new PackagedProduct("Beans", BigDecimal.valueOf(0.65));
        PackagedProduct bottleOfWater2 = new PackagedProduct("BottleOfWater", BigDecimal.valueOf(0.35));
        //when
        register.addProduct(beans);
        register.addProduct(bottleOfWater);
        register.addProduct(beans2);
        register.addProduct(bottleOfWater2);
        Receipt result = register.finishTransaction();

        //then
        assertEquals(new Money(BigDecimal.valueOf(2)), result.total);
        assertEquals(2, result.LineItems.size());

        assertEquals(2, result.LineItems.get(0).productAmount);
        assertEquals(new Money(BigDecimal.valueOf(1.30)), result.LineItems.get(0).productTotal);
        assertEquals("Beans", result.LineItems.get(0).productName);

        assertEquals(2, result.LineItems.get(1).productAmount);
        assertEquals(new Money(BigDecimal.valueOf(0.70)), result.LineItems.get(1).productTotal);
        assertEquals("BottleOfWater", result.LineItems.get(1).productName);
    }

    @Test
    public void shouldAddLooseProduct() {
        //given
        CashRegister register = new CashRegister(Map.of());
        LooseProduct sausage = new LooseProduct("Slaska", BigDecimal.valueOf(15.50), 0.833);
        //when
        register.addProduct(sausage);
        Receipt result = register.finishTransaction();

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
        CashRegister register = new CashRegister(Map.of());

        LooseProduct sausage = new LooseProduct("Slaska", BigDecimal.valueOf(2), 2);
        LooseProduct ham = new LooseProduct("Krakowska", BigDecimal.valueOf(1), 2);
        LooseProduct ham2 = new LooseProduct("Krakowska", BigDecimal.valueOf(1), 2);
        LooseProduct sausage2 = new LooseProduct("Slaska", BigDecimal.valueOf(2), 2);

        //when
        register.addProduct(sausage);
        register.addProduct(ham);
        register.addProduct(ham2);
        register.addProduct(sausage2);
        Receipt result = register.finishTransaction();

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

    @Test
    public void shouldAddMultipleDiscountTwoForOneProducts() {
        //given
        HashMap<String,Promotion> promotionMap = new HashMap<>();
        promotionMap.put("Beans", new Promotion());
        CashRegister register = new CashRegister(promotionMap);

        PackagedProduct beans = new PackagedProduct("Beans", BigDecimal.valueOf(0.65));
        PackagedProduct beans2 = new PackagedProduct("Beans", BigDecimal.valueOf(0.65));

        //when
        register.addProduct(beans);
        register.addProduct(beans2);
        Receipt result = register.finishTransaction();
        //then
        assertEquals(new Money(BigDecimal.valueOf(0.65)), result.total);
        assertEquals(2, result.LineItems.size());

        assertEquals(2, result.LineItems.get(0).productAmount);
        assertEquals(new Money(BigDecimal.valueOf(1.30)), result.LineItems.get(0).productTotal);
        assertEquals("Beans", result.LineItems.get(0).productName);

        assertEquals("Two for one: Beans", result.LineItems.get(1).productName);
        assertEquals(new Money(BigDecimal.valueOf(-0.65)), result.LineItems.get(1).productTotal);
        assertEquals(1, result.LineItems.get(1).productAmount);
    }

    @Test
    public void shouldAddMultipleDiscountEightPlusEightProducts() {
        //given
        HashMap<String,Promotion> promotionMap = new HashMap<>();
        promotionMap.put("Beans", new Promotion());
        CashRegister register = new CashRegister(promotionMap);

        PackagedProduct beans = new PackagedProduct("Beans", BigDecimal.valueOf(0.65));
        PackagedProduct beans2 = new PackagedProduct("Beans", BigDecimal.valueOf(0.65));
        PackagedProduct beans3 = new PackagedProduct("Beans", BigDecimal.valueOf(0.65));
        PackagedProduct beans4 = new PackagedProduct("Beans", BigDecimal.valueOf(0.65));
        PackagedProduct beans5 = new PackagedProduct("Beans", BigDecimal.valueOf(0.65));
        PackagedProduct beans6 = new PackagedProduct("Beans", BigDecimal.valueOf(0.65));
        PackagedProduct beans7 = new PackagedProduct("Beans", BigDecimal.valueOf(0.65));
        PackagedProduct beans8 = new PackagedProduct("Beans", BigDecimal.valueOf(0.65));
        PackagedProduct beans9 = new PackagedProduct("Beans", BigDecimal.valueOf(0.65));
        PackagedProduct beans10 = new PackagedProduct("Beans", BigDecimal.valueOf(0.65));
        PackagedProduct beans11 = new PackagedProduct("Beans", BigDecimal.valueOf(0.65));
        PackagedProduct beans12 = new PackagedProduct("Beans", BigDecimal.valueOf(0.65));
        PackagedProduct beans13 = new PackagedProduct("Beans", BigDecimal.valueOf(0.65));
        PackagedProduct beans14 = new PackagedProduct("Beans", BigDecimal.valueOf(0.65));
        PackagedProduct beans15 = new PackagedProduct("Beans", BigDecimal.valueOf(0.65));
        PackagedProduct beans16 = new PackagedProduct("Beans", BigDecimal.valueOf(0.65));


        //when
        register.addProduct(beans);
        register.addProduct(beans2);
        register.addProduct(beans3);
        register.addProduct(beans4);
        register.addProduct(beans5);
        register.addProduct(beans6);
        register.addProduct(beans7);
        register.addProduct(beans8);
        register.addProduct(beans9);
        register.addProduct(beans10);
        register.addProduct(beans11);
        register.addProduct(beans12);
        register.addProduct(beans13);
        register.addProduct(beans14);
        register.addProduct(beans15);
        register.addProduct(beans16);


        Receipt result = register.finishTransaction();
        //then
        assertEquals(new Money(BigDecimal.valueOf(5.20)), result.total);
        assertEquals(2, result.LineItems.size());

        assertEquals(16, result.LineItems.get(0).productAmount);
        assertEquals(new Money(BigDecimal.valueOf(10.40)), result.LineItems.get(0).productTotal);
        assertEquals("Beans", result.LineItems.get(0).productName);

        assertEquals("Eight plus eight: Beans", result.LineItems.get(1).productName);
        assertEquals(new Money(BigDecimal.valueOf(-5.20)), result.LineItems.get(1).productTotal);
        assertEquals(1, result.LineItems.get(1).productAmount);
    }



}