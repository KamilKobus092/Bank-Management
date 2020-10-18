package pl.kkobusprogramming.bankmanagement.Account.exceptions;

public class NotEnoughMoneyException extends RuntimeException {
    public NotEnoughMoneyException() {
        super(String.format("You don't have enough money"));
    }
}
