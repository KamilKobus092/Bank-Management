package pl.kkobusprogramming.bankmanagement.Account.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class SubAccount {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("balance")
    private Double balance;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "account_id")
    @JsonIgnore
    private Account account;

    public SubAccount() {
    }

    public SubAccount(String currency, Double balance, Account account) {
        this.currency = currency;
        this.balance = balance;
        this.account = account;
    }
}
