package pl.kkobusprogramming.bankmanagement.Account.exceptions;

public class AdminException extends RuntimeException {
    public AdminException() {
        super(String.format("Please contact the administrator to explain the error"));
    }
}
