package pl.kkobusprogramming.bankmanagement.Account.exceptions;

public class UnknownCurrencyException extends RuntimeException {
    public UnknownCurrencyException() {
        super(String.format("Unknown currency, only PLN and USD can be exchanged."));
    }
}
