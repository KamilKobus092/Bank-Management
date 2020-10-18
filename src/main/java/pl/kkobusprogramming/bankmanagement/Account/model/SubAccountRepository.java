package pl.kkobusprogramming.bankmanagement.Account.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubAccountRepository extends JpaRepository<SubAccount, Long> {

    Optional<SubAccount> findSubAccountByCurrencyAndAccount_Pesel(String currency, String pesel);

    default Optional<SubAccount> findSubAccount(String currency, String pesel) {
        return findSubAccountByCurrencyAndAccount_Pesel(currency, pesel);
    }
}
