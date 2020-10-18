package pl.kkobusprogramming.bankmanagement.Account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import pl.kkobusprogramming.bankmanagement.Account.api.AccountService;
import pl.kkobusprogramming.bankmanagement.Account.exceptions.InvalidPeselNumberException;
import pl.kkobusprogramming.bankmanagement.Account.model.Account;
import pl.kkobusprogramming.bankmanagement.Account.model.AccountRepository;
import pl.kkobusprogramming.bankmanagement.Account.model.PostRequestAccount;
import pl.kkobusprogramming.bankmanagement.Account.model.SubAccountRepository;
import pl.kkobusprogramming.bankmanagement.Currency.api.CurrencyController;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasLength;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test cases for AccountService")
class AccountServiceTest {

    @Mock
    AccountRepository accountRepository;

    @Mock
    SubAccountRepository subAccountRepository;

    @Mock
    CurrencyController currencyController;

    @Autowired
    AccountService accountService;

    @BeforeEach
    void setUp() {
        accountService = new AccountService(accountRepository, subAccountRepository, currencyController);
    }

    @Test
    @DisplayName("it should return new account")
    void itShouldReturnNewAccount() {
        Account account = new Account("01010100313", "test", null);
        doReturn(account).when(accountRepository).save(any(Account.class));
        Account result = accountService.createAccount(new PostRequestAccount("01010100313", "test", 100.0));
        verify(accountRepository).save(any(Account.class));
        assertThat(result.getFullname(), is(equalTo("test")));
        assertThat(account.getPesel(), allOf(
                equalTo("01010100313"),
                notNullValue(),
                hasLength(11)
        ));

    }

    @Test
    @DisplayName("it should return exception when account has incorrect pesel")
    void itShouldReturnExceptionWhenAccountHasIncorrecetPesel() {
        assertThrows(InvalidPeselNumberException.class, () -> accountService.createAccount(new PostRequestAccount("010x0100a13", "test", 100.0)));
    }
}