package pl.kkobusprogramming.bankmanagement.Account.exceptions;

public class InvalidPeselNumberException extends RuntimeException {
    public InvalidPeselNumberException(String message) {
        super(String.format(message));
    }
}
