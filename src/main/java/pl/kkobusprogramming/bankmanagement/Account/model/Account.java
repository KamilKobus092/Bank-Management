package pl.kkobusprogramming.bankmanagement.Account.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Getter
@Setter
public class Account {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "[\\d]{11}")
    @Column(unique = true)
    @JsonProperty("pesel")
    private String pesel;

    @JsonProperty("fullname")
    private String fullname;

    @JsonProperty("subAccounts")
    @OneToMany(mappedBy = "account")
    private List<SubAccount> subAccounts;

    public Account() {
    }

    public Account(String pesel, String fullname, List<SubAccount> subAccounts) {
        this.pesel = pesel;
        this.fullname = fullname;
        this.subAccounts = subAccounts;
    }
}