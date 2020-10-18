package pl.kkobusprogramming.bankmanagement.Account;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.kkobusprogramming.bankmanagement.Account.model.Account;
import pl.kkobusprogramming.bankmanagement.Account.model.AccountRepository;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@DisplayName("Test cases for AccountRepository")
class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @DisplayName("this should return an account with pesel")
    @Test()
    void itShouldReturnAccountByPesel() {
        accountRepository.saveAndFlush(new Account("09010100400", "Test", null));
        Optional<Account> account = accountRepository.findAccountByPesel("09010100400");
        assertTrue(account.isPresent());
        assertThat(account.get().getFullname(), is(equalTo("Test")));
    }
}