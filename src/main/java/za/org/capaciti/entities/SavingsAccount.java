package za.org.capaciti.entities;

public class SavingsAccount extends Account {
    private double interestRate;

    public SavingsAccount(String accountNumber, double initialBalance, double interestRate) {
        super(accountNumber, initialBalance);
        this.interestRate = interestRate;
    }

    @Override
    public void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be positive");
        }
        balance += amount;
        System.out.println("Deposited R" + amount + " to savings account " + accountNumber);
    }

    @Override
    public boolean withdraw(double amount) throws InsufficientFundsException, InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be positive");
        }
        if (balance < amount) {
            throw new InsufficientFundsException("Insufficient funds in savings account " + accountNumber);
        }
        balance -= amount;
        System.out.println("Withdrawn R" + amount + " from savings account " + accountNumber);
        return false;
    }

    @Override
    public String getAccountInfo() {
        return "Savings Account: " + accountNumber + ", Balance: R" + balance + ", Interest Rate: " + (interestRate * 100) + "%";
    }

    public void addInterest() {
        double interest = balance * interestRate;
        balance += interest;
        System.out.println("Added R" + interest + " interest to savings account " + accountNumber);
    }
}