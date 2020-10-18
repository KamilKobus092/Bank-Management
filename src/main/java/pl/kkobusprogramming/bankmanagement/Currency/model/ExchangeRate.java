package pl.kkobusprogramming.bankmanagement.Currency.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRate {
    private String table;
    private String currency;
    private String code;
    private List<Rates> rates;

    public ExchangeRate() {
    }

    public ExchangeRate(String table, String currency, String code, List<Rates> rates) {
        this.table = table;
        this.currency = currency;
        this.code = code;
        this.rates = rates;
    }
}
