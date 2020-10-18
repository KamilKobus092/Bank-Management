package pl.kkobusprogramming.bankmanagement.Account.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestAccount {
    @JsonProperty("pesel")
    private String pesel;

    @JsonProperty("fullname")
    private String fullname;

    @JsonProperty("balance")
    private Double balance;

    public PostRequestAccount(String pesel, String fullname, Double balance) {
        this.pesel = pesel;
        this.fullname = fullname;
        this.balance = balance;
    }
}
