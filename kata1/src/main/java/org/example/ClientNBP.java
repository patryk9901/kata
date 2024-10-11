package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

public class ClientNBP {

    private Map<String, BigDecimal> exchangeRates;

    public ClientNBP() {
        this.exchangeRates = new HashMap<>();

        exchangeRates.put("EUR", BigDecimal.valueOf(4.00));
        exchangeRates.put("USD", BigDecimal.valueOf(3.00));
        exchangeRates.put("GBP", BigDecimal.valueOf(5.00));
    }

    public BigDecimal getExchangeRate(Currency currency) {
        String currencyCode = currency.getCurrencyCode();

        if (exchangeRates.containsKey(currencyCode)) {
            return exchangeRates.get(currencyCode);
        } else {
            throw new IllegalArgumentException("Kurs dla waluty " + currencyCode + " nie jest dostępny.");
        }
    }

    public BigDecimal convertFromPLN(BigDecimal amountInPLN, Currency targetCurrency) {
        BigDecimal exchangeRate = getExchangeRate(targetCurrency);
        return amountInPLN.divide(exchangeRate, 2, RoundingMode.HALF_UP);
    }
}