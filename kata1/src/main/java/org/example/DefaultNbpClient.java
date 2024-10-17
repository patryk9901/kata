package org.example;

import com.google.gson.Gson;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Currency;

public class DefaultNbpClient implements NbpHttpClient {

    private final HttpClient client;

    public DefaultNbpClient(HttpClient client) {
        this.client = client;
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
        Gson gson = new Gson();

        ExchangeRateResponse exchangeRateResponse = gson.fromJson(responseBody, ExchangeRateResponse.class);

        return exchangeRateResponse.getRates()[0].getMid();
    }

}
