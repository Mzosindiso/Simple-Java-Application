package za.org.capaciti.entities;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


public class BankAccountManagement {
    private static final List<Customer> customers = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            displayMainMenu();
            int choice = getUserChoice(5);

            switch (choice) {
                case 1:
                    manageCustomers();
                    break;
                case 2:
                    manageAccounts();
                    break;
                case 3:
                    manageLoans();
                    break;
                case 4:
                    displayAllInfo();
                    break;
                case 5:
                    System.out.println("Thank you for using the Bank Account Management System. Goodbye!");
                    return;
            }
        }
    }

    public static void displayAllInfo() {
        System.out.println("\n=== All Customers Information ===");
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            for (Customer customer : customers) {
                System.out.println(customer.getDetails());
                System.out.println(customer.getAccountsAndLoans());
                System.out.println("-----------------------------");
            }
        }
    }


    private static void displayMainMenu() {
        System.out.println("\n=== Bank Management System ===");
        System.out.println("1. Manage Customers");
        System.out.println("2. Manage Accounts");
        System.out.println("3. Manage Loans");
        System.out.println("4. Display All Information");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getUserChoice(int max) {
        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= max) {
                    return choice;
                } else {
                    System.out.print("Invalid choice. Please enter a number between " + 1 + " and " + max + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }

    private static void manageCustomers() {
        while (true) {
            System.out.println("\n=== Manage Customers ===");
            System.out.println("1. Add New Customer");
            System.out.println("2. View Customer Details");
            System.out.println("3. Update Customer Address");
            System.out.println("4. Return to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = getUserChoice(4);

            switch (choice) {
                case 1:
                    addNewCustomer();
                    break;
                case 2:
                    viewCustomerDetails();
                    break;
                case 3:
                    updateCustomerAddress();
                    break;
                case 4:
                    return;
            }
        }
    }

    private static void addNewCustomer() {
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter customer ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter street address: ");
        String street = scanner.nextLine();
        System.out.print("Enter city: ");
        String city = scanner.nextLine();
        System.out.print("Enter state: ");
        String state = scanner.nextLine();
        System.out.print("Enter zip code: ");
        String zipCode = scanner.nextLine();

        Address address = new Address(street, city, state, zipCode);
        Customer newCustomer = new Customer(name, id, address);
        customers.add(newCustomer);

        System.out.println("New customer added successfully!");
    }

    private static void viewCustomerDetails() {
        System.out.print("Enter customer ID: ");
        String id = scanner.nextLine();
        Customer customer = findCustomerById(id);
        if (customer != null) {
            System.out.println(customer.getDetails());
            System.out.println(customer.getAccountsAndLoans());
        } else {
            System.out.println("Customer not found.");
        }
    }



    private static void updateCustomerAddress() {
        System.out.print("Enter customer ID: ");
        String id = scanner.nextLine();
        Customer customer = findCustomerById(id);
        if (customer != null) {
            System.out.print("Enter new street address: ");
            String street = scanner.nextLine();
            System.out.print("Enter new city: ");
            String city = scanner.nextLine();
            System.out.print("Enter new state: ");
            String state = scanner.nextLine();
            System.out.print("Enter new zip code: ");
            String zipCode = scanner.nextLine();

            Address newAddress = new Address(street, city, state, zipCode);
            customer.setAddress(newAddress);
            System.out.println("Customer address updated successfully!");
        } else {
            System.out.println("Customer not found.");
        }
    }

    private static Account findAccountByNumber(String accountNumber) {
        for (Customer customer : customers) {
            for (Account account : customer.getAccounts()) {
                if (account.getAccountNumber().equals(accountNumber)) {
                    return account;
                }
            }
        }
        return null;
    }

    private static void manageAccounts() {
        while (true) {
            System.out.println("\n=== Manage Accounts ===");
            System.out.println("1. Open New Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. View Account Balance");
            System.out.println("5. Return to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = getUserChoice(5);

            switch (choice) {
                case 1:
                    openNewAccount();
                    break;
                case 2:
                    depositToAccount();
                    break;
                case 3:
                    withdrawFromAccount();
                    break;
                case 4:
                    viewAccountBalance();
                    break;
                case 5:
                    return;
            }
        }
    }

    private static void openNewAccount() {
        System.out.print("Enter customer ID: ");
        String customerId = scanner.nextLine();
        Customer customer = findCustomerById(customerId);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        System.out.println("Select account type:");
        System.out.println("1. Savings Account");
        System.out.println("2. Credit Account");
        int accountType = getUserChoice(2);

        System.out.print("Enter initial balance: ");
        double initialBalance = getPositiveDouble();

        String accountNumber = "A" + (int) (Math.random() * 10000);
        Account newAccount;

        if (accountType == 1) {
            System.out.print("Enter interest rate (e.g., 0.05 for 5%): ");
            double interestRate = getPositiveDouble();
            newAccount = new SavingsAccount(accountNumber, initialBalance, interestRate);
        } else {
            System.out.print("Enter overdraft limit: ");
            double overdraftLimit = getPositiveDouble();
            newAccount = new CreditAccount(accountNumber, initialBalance, overdraftLimit);
        }

        customer.addAccount(newAccount);
        System.out.println("New account opened successfully! Account number: " + accountNumber);
    }



    private static void depositToAccount() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        Account account = findAccountByNumber(accountNumber);
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.print("Enter deposit amount: ");
        double amount = getPositiveDouble();

        try {
            account.deposit(amount);
            System.out.println("Deposit successful. \n ===================== " +
                    "\n New balance: R" + account.getBalance() + "\n =====================");
        } catch (InvalidAmountException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static double getPositiveDouble() {
        return scanner.nextDouble();
    }



    private static void withdrawFromAccount() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        Account account = findAccountByNumber(accountNumber);
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.print("Enter withdrawal amount: ");
        double amount = getPositiveDouble();

        try {
            account.withdraw(amount);
            System.out.println("Withdrawal successful.\n ===================== \n" +
                    " New balance: R" + account.getBalance() + "\n =====================");
        } catch (InsufficientFundsException | InvalidAmountException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    private static void viewAccountBalance() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        Account account = findAccountByNumber(accountNumber);
        if (account != null) {
            System.out.println(account.getAccountInfo());
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void manageLoans() {
        while (true) {
            System.out.println("\n=== Manage Loans ===");
            System.out.println("1. Apply for New Loan");
            System.out.println("2. View Loan Details");
            System.out.println("3. Return to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = getUserChoice(3);

            switch (choice) {
                case 1:
                    applyForNewLoan();
                    break;
                case 2:
                    viewLoanDetails();
                    break;
                case 3:
                    return;
            }
        }
    }

    private static void applyForNewLoan() {
        System.out.print("Enter customer ID: ");
        String customerId = scanner.nextLine();
        Customer customer = findCustomerById(customerId);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        System.out.print("Enter loan amount: ");
        double amount = getPositiveDouble();
        System.out.print("Enter interest rate (e.g., 0.05 for 5%): ");
        double interestRate = getPositiveDouble();
        System.out.print("Enter loan term in months: ");
        int term = getPositiveInt();

        try {
            String loanId = "L" + (int) (Math.random() * 10000);
            Loan newLoan = new Loan(loanId, amount, interestRate, term);
            customer.addLoan(newLoan);
            System.out.println("Loan application successful! Loan ID: " + loanId);
            System.out.println("Monthly payment: R" + String.format("%.2f", newLoan.calculateMonthlyPayment()));
        } catch (InvalidAmountException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static Customer findCustomerById(String customerId) {
        for (Customer customer : customers) {
            if (customer.getId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }

    private static int getPositiveInt() {
        return scanner.nextInt();
    }

    private static void viewLoanDetails() {
        System.out.print("Enter customer ID: ");
        String customerId = scanner.nextLine();
        Customer customer = findCustomerById(customerId);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        List<Loan> loans = customer.getLoans();
        if (loans.isEmpty()) {
            System.out.println("This customer has no loans.");
        } else {
            for (Loan loan : loans) {
                System.out.println(loan.getLoanInfo());
                System.out.println("Monthly payment: R" + String.format("%.2f", loan.calculateMonthlyPayment()));
            }
        }
    }

}