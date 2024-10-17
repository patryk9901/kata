package org.example;

public class ExchangeRateResponse {
    private String currency;
    private String code;
    private Rate[] rates;

    public Rate[] getRates(){
        return rates;
    }

}
