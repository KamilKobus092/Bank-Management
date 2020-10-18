package pl.kkobusprogramming.bankmanagement.Account.exceptions;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(String accountLookupKey, Object accountLookupValue) {
        super(String.format("Could not find account with %s == %s", accountLookupKey, accountLookupValue));
    }
}
