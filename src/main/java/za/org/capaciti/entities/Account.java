package za.org.capaciti.entities;

public abstract class Account {
    protected String accountNumber;
    protected double balance;

    public Account(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    public abstract void deposit(double amount) throws InvalidAmountException;
    public abstract boolean withdraw(double amount) throws InsufficientFundsException, InvalidAmountException;
    public abstract String getAccountInfo();

    public double getBalance() {
        return balance;
    }
}