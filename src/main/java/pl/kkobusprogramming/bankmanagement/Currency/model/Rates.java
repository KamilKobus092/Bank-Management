package pl.kkobusprogramming.bankmanagement.Currency.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Rates {
    private String no;
    private String effectiveDate;
    private Double bid;
    private Double ask;

    public Rates() {
    }

    public Rates(String no, String effectiveDate, Double bid, Double ask) {
        this.no = no;
        this.effectiveDate = effectiveDate;
        this.bid = bid;
        this.ask = ask;
    }
}
