package pl.kkobusprogramming.bankmanagement.Account;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.kkobusprogramming.bankmanagement.Account.model.Account;
import pl.kkobusprogramming.bankmanagement.Account.model.AccountRepository;
import pl.kkobusprogramming.bankmanagement.Account.model.SubAccount;
import pl.kkobusprogramming.bankmanagement.Account.model.SubAccountRepository;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@DisplayName("Test cases for SubAccount")
class SubAccountRepositoryTest {
    @Autowired
    SubAccountRepository subAccountRepository;

    @Autowired
    AccountRepository accountRepository;

    @DisplayName("this should return an sub_account by currency and pesel")
    @Test()
    void itShouldReturnAccountByCurrencyAndPesel() {
        Account account = accountRepository.saveAndFlush(new Account("09010100400", "Test", null));
        subAccountRepository.saveAndFlush(new SubAccount("PLN", 100.0, account));
        Optional<SubAccount> subAccount = subAccountRepository.findSubAccountByCurrencyAndAccount_Pesel("PLN", "09010100400");
        assertThat(subAccount.get().getCurrency(), is(equalTo("PLN")));
        assertThat(subAccount.get().getBalance(), is(equalTo(100.0)));
    }
}