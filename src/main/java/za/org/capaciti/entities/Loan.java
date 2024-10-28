package za.org.capaciti.entities;

public class Loan {
    private final String loanId;
    private final double amount;
    private final double interestRate;
    private final int term; // in months

    public Loan(String loanId, double amount, double interestRate, int term) throws InvalidAmountException {
        if (amount <= 0 || interestRate <= 0 || term <= 0) {
            throw new InvalidAmountException("Loan amount, interest rate, and term must be positive");
        }
        this.loanId = loanId;
        this.amount = amount;
        this.interestRate = interestRate;
        this.term = term;
    }

    public String getLoanInfo() {
        return "Loan ID: " + loanId + ", Amount: R" + amount + ", Interest Rate: " + (interestRate * 100) + "%, Term: " + term + " months";
    }

    public double calculateMonthlyPayment() {
        double monthlyRate = interestRate / 12;
        return (amount * monthlyRate * Math.pow(1 + monthlyRate, term)) / (Math.pow(1 + monthlyRate, term) - 1);
    }
}