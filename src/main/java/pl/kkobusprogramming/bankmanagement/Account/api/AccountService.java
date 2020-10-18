package pl.kkobusprogramming.bankmanagement.Account.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kkobusprogramming.bankmanagement.Account.exceptions.NotEnoughMoneyException;
import pl.kkobusprogramming.bankmanagement.Account.exceptions.UnknownCurrencyException;
import pl.kkobusprogramming.bankmanagement.Account.model.*;
import pl.kkobusprogramming.bankmanagement.Account.validations.PeselValidator;
import pl.kkobusprogramming.bankmanagement.Currency.api.CurrencyController;
import pl.kkobusprogramming.bankmanagement.Currency.model.Currency;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final SubAccountRepository subAccountRepository;
    private final CurrencyController currencyController;

    public Account createAccount(PostRequestAccount postRequestAccount) {
        validateIsAdult(postRequestAccount.getPesel());
        Account account = new Account(postRequestAccount.getPesel(), postRequestAccount.getFullname(), null);
        account.setSubAccounts(createSubAccountList(postRequestAccount.getBalance(), account));
        return accountRepository.save(account);
    }

    public Optional<Account> findByPesel(String pesel) {
        return accountRepository.findAccountByPesel(pesel);
    }

    public Optional<SubAccount> findByPeselAndCurrencyType(String currency, String pesel) {
        return subAccountRepository.findSubAccount(currency, pesel);
    }

    public Optional<SubAccount> deposit(String currency, String pesel, Double amount) {
        SubAccount subAccount = subAccountRepository.findSubAccount(currency, pesel).get();
        subAccount.setBalance(subAccount.getBalance() + amount);
        subAccountRepository.save(subAccount);
        return subAccountRepository.findSubAccount(currency, pesel);
    }

    public Optional<Account> exchangeCurrency(String pesel, double amount, String currency) {
        if (!currency.equalsIgnoreCase("USD") && !currency.equalsIgnoreCase("PLN")) {
            throw new UnknownCurrencyException();
        }
        SubAccount subAccountPLN = subAccountRepository.findSubAccount("PLN", pesel).get();
        SubAccount subAccountUSD = subAccountRepository.findSubAccount("USD", pesel).get();
        switch (Currency.valueOf(currency)) {
            case PLN:
                isEnoughMoney(subAccountPLN.getBalance(), amount);
                subAccountPLN.setBalance(subAccountPLN.getBalance() - amount);
                subAccountUSD.setBalance(BigDecimal.valueOf(subAccountUSD.getBalance() + (amount / currencyController.getBid("USD")))
                        .setScale(2, RoundingMode.HALF_UP)
                        .doubleValue());
                break;
            case USD:
                isEnoughMoney(subAccountUSD.getBalance(), amount);
                subAccountUSD.setBalance(subAccountUSD.getBalance() - amount);
                subAccountPLN.setBalance(BigDecimal.valueOf(subAccountPLN.getBalance() + (amount * currencyController.getAsk("USD")))
                        .setScale(2, RoundingMode.HALF_UP)
                        .doubleValue());
                break;
        }
        subAccountRepository.save(subAccountPLN);
        subAccountRepository.save(subAccountUSD);
        return accountRepository.findAccountByPesel(pesel);
    }

    private boolean validateIsAdult(String pesel) {
        PeselValidator peselValidator = new PeselValidator();
        return peselValidator.isAdult(pesel);
    }

    private List<SubAccount> createSubAccountList(Double balance, Account account) {
        List<SubAccount> subAccounts = new ArrayList<>();
        subAccounts.add(createSubAccount("PLN", balance, account));
        subAccounts.add(createSubAccount("USD", 0.0, account));
        return subAccounts;
    }

    private SubAccount createSubAccount(String currency, Double balance, Account account) {
        SubAccount subAccount = new SubAccount();
        subAccount.setCurrency(currency);
        subAccount.setBalance(balance);
        subAccount.setAccount(account);
        subAccountRepository.save(subAccount);
        return subAccount;
    }

    private boolean isEnoughMoney(Double currentBalance, Double amount) {
        if (currentBalance < amount) {
            throw new NotEnoughMoneyException();
        } else {
            return true;
        }
    }
}
