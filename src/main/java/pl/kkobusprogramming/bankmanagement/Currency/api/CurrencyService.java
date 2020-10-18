package pl.kkobusprogramming.bankmanagement.Currency.api;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.kkobusprogramming.bankmanagement.Currency.model.ExchangeRate;

@Service
public class CurrencyService {

    private final String url = "http://api.nbp.pl/api/exchangerates/rates/c/";
    private RestTemplate restTemplate = new RestTemplate();


    protected Double getCurrencyAskParametr(String code) {
        ExchangeRate result = restTemplate.getForObject(url + code, ExchangeRate.class);
        return result.getRates().get(0).getAsk();
    }

    protected Double getCurrencyBidParametr(String code) {
        ExchangeRate result = restTemplate.getForObject(url + code, ExchangeRate.class);
        return result.getRates().get(0).getBid();
    }
}
