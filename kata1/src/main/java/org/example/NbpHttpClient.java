package org.example;

import java.math.BigDecimal;
import java.util.Currency;

public interface NbpHttpClient {

    public BigDecimal getExchangeRateFromNBP(Currency currency);
}
