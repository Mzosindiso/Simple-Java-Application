package za.org.capaciti.entities;
import java.util.ArrayList;
import java.util.List;

public class Customer extends Person {
    private final List<Account> accounts;
    private final List<Loan> loans;

    public Customer(String name, String id, Address address) {
        super(name, id, String.valueOf(address));
        this.accounts = new ArrayList<>();
        this.loans = new ArrayList<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void addLoan(Loan loan) {
        loans.add(loan);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "accounts=" + accounts +
                ", loans=" + loans +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", address=" + address +
                '}';
    }

    @Override
    public String getDetails() {
        return "Customer ID: " + getId() + "\n" +
                "Name: " + getName() + "\n" +
                "Address: " + getAddress().toString();
    }

    private Object getAddress() {
        return address;
    }

    public String getId() {
        return id;
    }

    private String getName() {
        return name;
    }


    public String getAccountsAndLoans() {
        StringBuilder sb = new StringBuilder();
        sb.append("Accounts:\n");
        for (Account account : accounts) {
            sb.append("- ").append(account.getAccountInfo()).append("\n");
        }
        sb.append("Loans:\n");
        for (Loan loan : loans) {
            sb.append("- ").append(loan.getLoanInfo()).append("\n");
        }
        return sb.toString();
    }

    public List<Loan> getLoans() {
        return List.of();
    }

    public void setAddress(Address newAddress) {
        this.address = String.valueOf(newAddress);
    }

    public Account[] getAccounts() {
        return accounts.toArray(new Account[0]);
    }
}