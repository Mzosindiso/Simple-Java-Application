package za.org.capaciti.entities;

public class CreditAccount extends Account {
    private final double overdraftLimit;

    public CreditAccount(String accountNumber, double initialBalance, double overdraftLimit) {
        super(accountNumber, initialBalance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be positive");
        }
        balance += amount;
        System.out.println("Deposited R" + amount + " to checking account " + accountNumber);
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException, InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be positive");
        }
        if (balance + overdraftLimit < amount) {
            throw new InsufficientFundsException("Exceeds overdraft limit in checking account " + accountNumber);
        }
        balance -= amount;
        System.out.println("Withdrawn R" + amount + " from checking account " + accountNumber);
    }

    @Override
    public String getAccountInfo() {
        return "Checking Account: " + accountNumber + ", Balance: R" + balance + ", Overdraft Limit: R" + overdraftLimit;
    }
}