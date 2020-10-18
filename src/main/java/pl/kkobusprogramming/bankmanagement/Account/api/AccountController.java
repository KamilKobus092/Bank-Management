package pl.kkobusprogramming.bankmanagement.Account.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kkobusprogramming.bankmanagement.Account.exceptions.AccountNotFoundException;
import pl.kkobusprogramming.bankmanagement.Account.exceptions.AdminException;
import pl.kkobusprogramming.bankmanagement.Account.model.Account;
import pl.kkobusprogramming.bankmanagement.Account.model.PostRequestAccount;
import pl.kkobusprogramming.bankmanagement.Account.model.SubAccount;

@RequiredArgsConstructor
@RestController()
@RequestMapping(
        value = "/account",
        produces = {"application/json;charset=utf-8"})
public class AccountController {

    private final AccountService accountService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Account> createAccount(@RequestBody PostRequestAccount account) {
        return ResponseEntity.ok(accountService.createAccount(account));
    }

    @GetMapping("/{pesel}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Account> getAccountByPesel(@PathVariable String pesel) {
        return ResponseEntity.ok(
                accountService
                        .findByPesel(pesel)
                        .orElseThrow(() -> new AccountNotFoundException("pesel", pesel)));
    }

    @GetMapping("subAccount/{pesel}/{currency}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<SubAccount> getSubAccount(@PathVariable String pesel, @PathVariable String currency) {
        return ResponseEntity.ok(
                accountService
                        .findByPeselAndCurrencyType(currency, pesel)
                        .orElseThrow(() -> new AccountNotFoundException("pesel", pesel)));
    }

    @PatchMapping("subAccount/{pesel}/{amount}/{currency}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<SubAccount> deposit(@PathVariable String pesel, @PathVariable Double amount, @PathVariable String currency) {
        return ResponseEntity.ok(
                accountService
                        .deposit(currency, pesel, amount)
                        .orElseThrow(() -> new AdminException()));
    }

    @PatchMapping("subAccount/exchange/{pesel}/{amount}/{currency}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Account> exchangeCurrency(@PathVariable String pesel, @PathVariable Double amount, @PathVariable String currency) {
        return ResponseEntity.ok(
                accountService
                        .exchangeCurrency(pesel, amount, currency)
                        .orElseThrow(() -> new AdminException()));
    }
}
