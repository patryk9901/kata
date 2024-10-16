package org.example;

import com.google.gson.Gson;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

public class ClientNBP {

    private final Map<String, BigDecimal> exchangeRates;
    private final HttpClient client;

    public ClientNBP() {
        this.exchangeRates = new HashMap<>();
        this.client = HttpClient.newHttpClient();

        initializeExchangeRates();
    }

    private void initializeExchangeRates() {
        addCurrencyToMap("EUR");
        addCurrencyToMap("USD");
        addCurrencyToMap("GBP");
    }


    private void addCurrencyToMap(String currencyCode) {
        BigDecimal rate = getExchangeRateFromNBP(Currency.getInstance(currencyCode));
        if (rate != null) {
            exchangeRates.put(currencyCode, rate);
        }
    }

    public BigDecimal getExchangeRateFromNBP(Currency currency) {
        String url = String.format("https://api.nbp.pl/api/exchangerates/rates/A/%s/", currency.getCurrencyCode());

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return parseExchangeRate(response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    private BigDecimal parseExchangeRate(String responseBody) {
        var g = new Gson();
        String marker = "\"mid\":";
        int startIndex = responseBody.indexOf(marker) + marker.length();
        int endIndex = responseBody.indexOf('}', startIndex);
        String rateString = responseBody.substring(startIndex, endIndex).trim();

        return new BigDecimal(rateString);
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