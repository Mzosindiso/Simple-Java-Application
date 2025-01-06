package za.org.capaciti;

import za.org.capaciti.entities.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class BankAccountManagementGUI extends JFrame {
    private static final List<Customer> customers = new ArrayList<>();
    private JTextArea outputArea;

    // Custom color scheme
    private static final Color PRIMARY_COLOR = new Color(146, 0, 0);  // Dark blue
    private static final Color SECONDARY_COLOR = new Color(255, 215, 0);  // Gold
    private static final Color BACKGROUND_COLOR = new Color(251, 253, 255);  // Light blue
    private static final Color TEXT_COLOR = new Color(33, 33, 33);  // Dark gray

    public BankAccountManagementGUI() {
        setTitle("Bank Account Management System");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(BACKGROUND_COLOR);

        JLabel logoLabel;
        try {
            ImageIcon logoIcon = new ImageIcon(getClass().getResource("/OIP.jpg"));
            if (logoIcon.getImageLoadStatus() == MediaTracker.COMPLETE) {
                Image scaledLogo = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                logoLabel = new JLabel(new ImageIcon(scaledLogo));
            } else {
                throw new Exception("Image not found");
            }
        } catch (Exception e) {
            //If image is not found in resources, this is a fallback to text
            logoLabel = new JLabel("Bank Logo");
            logoLabel.setFont(new Font("Arial", Font.BOLD, 18));
            logoLabel.setForeground(Color.WHITE);
        }
        logoLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(PRIMARY_COLOR);
        topPanel.add(logoLabel, BorderLayout.WEST);
    
    

        JLabel titleLabel = new JLabel("Bank Account Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        topPanel.add(titleLabel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);

        JPanel buttonPanel = createButtonPanel();
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Arial", Font.PLAIN, 14));
        outputArea.setForeground(TEXT_COLOR);
        outputArea.setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(buttonPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.setBackground(BACKGROUND_COLOR);

        String[] buttonLabels = {"Manage Customers", "Manage Accounts", "Manage Loans", "Display All Information", "Exit"};
        for (String label : buttonLabels) {
            JButton button = createStyledButton(label);
            buttonPanel.add(button);
        }

        return buttonPanel;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 15));
        button.setFocusPainted(false);
        button.setBackground(SECONDARY_COLOR);
        button.setForeground(PRIMARY_COLOR);
        button.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addActionListener(e -> {
            switch (text) {
                case "Manage Customers":
                    manageCustomers();
                    break;
                case "Manage Accounts":
                    manageAccounts();
                    break;
                case "Manage Loans":
                    manageLoans();
                    break;
                case "Display All Information":
                    displayAllInfo();
                    break;
                case "Exit":
                    System.exit(0);
                    break;
            }
        });

        return button;
    }
    private void manageCustomers() {
        JDialog dialog = new JDialog(this, "Manage Customers", true);
        dialog.setLayout(new GridLayout(4, 1, 10, 10));
        dialog.setSize(300, 250);

        String[] buttonLabels = {"Add New Customer", "View Customer Details", "Update Customer Address", "Back to Main Menu"};
        for (String label : buttonLabels) {
            JButton button = createStyledButton(label);
            dialog.add(button);

            button.addActionListener(e -> {
                dialog.dispose();
                switch (label) {
                    case "Add New Customer":
                        addNewCustomer();
                        break;
                    case "View Customer Details":
                        viewCustomerDetails();
                        break;
                    case "Update Customer Address":
                        updateCustomerAddress();
                        break;
                }
            });
        }

        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void manageAccounts() {
        JDialog dialog = new JDialog(this, "Manage Accounts", true);
        dialog.setLayout(new GridLayout(5, 1, 10, 10));
        dialog.setSize(300, 300);

        String[] buttonLabels = {"Open New Account", "Deposit", "Withdraw", "View Account Balance", "Back to Main Menu"};
        for (String label : buttonLabels) {
            JButton button = createStyledButton(label);
            dialog.add(button);

            button.addActionListener(e -> {
                dialog.dispose();
                switch (label) {
                    case "Open New Account":
                        openNewAccount();
                        break;
                    case "Deposit":
                        depositToAccount();
                        break;
                    case "Withdraw":
                        withdrawFromAccount();
                        break;
                    case "View Account Balance":
                        viewAccountBalance();
                        break;
                }
            });
        }

        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void manageLoans() {
        JDialog dialog = new JDialog(this, "Loan Management", true);
        dialog.setLayout(new GridLayout(5, 1, 10, 10));
        dialog.setSize(300, 250);
    
        JButton applyButton = createStyledButton("Apply for Loan");
        JButton approveButton = createStyledButton("Approve Loan");
        JButton disapproveButton = createStyledButton("Disapprove Loan");
        JButton paymentButton = createStyledButton("Make Loan Payment");
        JButton backButton = createStyledButton("Back to Main Menu");
    
        applyButton.addActionListener(e -> {
            dialog.dispose();
            applyForLoan();
        });
    
        approveButton.addActionListener(e -> {
            dialog.dispose();
            approveLoan();
        });
    
        disapproveButton.addActionListener(e -> {
            dialog.dispose();
            disapproveLoan();
        });
    
        paymentButton.addActionListener(e -> {
            dialog.dispose();
            makeLoanPayment();
        });
    
        backButton.addActionListener(e -> {
            dialog.dispose();
        });
    
        dialog.add(applyButton);
        dialog.add(approveButton);
        dialog.add(disapproveButton);
        dialog.add(paymentButton);
        dialog.add(backButton);
    
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
    private void displayAllInfo() {
        StringBuilder info = new StringBuilder();
        info.append("=== All Customer Information ===\n\n");
    
        for (Customer customer : customers) {
            info.append(customer.getDetails()).append("\n");
            info.append(customer.getAccountsAndLoans()).append("\n");
            info.append("-----------------------------\n");
        }
    
        if (customers.isEmpty()) {
            info.append("No customers found.");
        }
    
        outputArea.setText(info.toString());
    }
    
    private void applyForLoan() {
        JDialog dialog = new JDialog(this, "Apply for Loan", true);
        dialog.setLayout(new GridLayout(5, 2, 10, 10));
        dialog.setSize(400, 250);
    
        JTextField customerIdField = new JTextField();
        JTextField amountField = new JTextField();
        JTextField interestRateField = new JTextField();
        JTextField termField = new JTextField();
    
        dialog.add(new JLabel("Customer ID:"));
        dialog.add(customerIdField);
        dialog.add(new JLabel("Loan Amount:"));
        dialog.add(amountField);
        dialog.add(new JLabel("Interest Rate (%):"));
        dialog.add(interestRateField);
        dialog.add(new JLabel("Term (months):"));
        dialog.add(termField);
    
        JButton submitButton = createStyledButton("Submit Application");
        submitButton.addActionListener(e -> {
            try {
                String customerId = customerIdField.getText();
                double amount = Double.parseDouble(amountField.getText());
                double interestRate = Double.parseDouble(interestRateField.getText()) / 100;
                int term = Integer.parseInt(termField.getText());
    
                Customer customer = findCustomerById(customerId);
                if (customer != null) {
                    String loanId = "L" + (int) (Math.random() * 10000);
                    try {
                        Loan newLoan = new Loan(loanId, amount, interestRate, term);
                        customer.addLoan(newLoan);
                        outputArea.setText("Loan application successful! Loan ID: " + loanId + 
                                           "\nMonthly payment: R" + String.format("%.2f", newLoan.calculateMonthlyPayment()));
                    } catch (InvalidAmountException ex) {
                        outputArea.setText("Error: " + ex.getMessage());
                    }
                } else {
                    outputArea.setText("Customer not found.");
                }
            } catch (NumberFormatException ex) {
                outputArea.setText("Invalid input. Please enter valid numbers.");
            }
            dialog.dispose();
        });
    
        dialog.add(submitButton);
    
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
    
    private void approveLoan() {
        String loanId = JOptionPane.showInputDialog(this, "Enter Loan ID to approve:");
        if (loanId != null && !loanId.isEmpty()) {
            for (Customer customer : customers) {
                for (Loan loan : customer.getLoans()) {
                    if (loan.getLoanInfo().equals(loanId)) {
                        loan.setApproved(true);
                        outputArea.setText("Loan " + loanId + " has been approved.");
                        return;
                    }
                }
            }
            outputArea.setText("Loan not found.");
        }
    }
    
    private void disapproveLoan() {
        String loanId = JOptionPane.showInputDialog(this, "Enter Loan ID to disapprove:");
        if (loanId != null && !loanId.isEmpty()) {
            for (Customer customer : customers) {
                for (Loan loan : customer.getLoans()) {
                    if (loan.getLoanInfo().equals(loanId)) {
                        loan.setApproved(false);
                        outputArea.setText("Loan " + loanId + " has been disapproved.");
                        return;
                    }
                }
            }
            outputArea.setText("Loan not found.");
        }
    }
    
    private void makeLoanPayment() {
        JDialog dialog = new JDialog(this, "Make Loan Payment", true);
        dialog.setLayout(new GridLayout(3, 2, 10, 10));
        dialog.setSize(300, 150);
    
        JTextField loanIdField = new JTextField();
        JTextField paymentAmountField = new JTextField();
    
        dialog.add(new JLabel("Loan ID:"));
        dialog.add(loanIdField);
        dialog.add(new JLabel("Payment Amount:"));
        dialog.add(paymentAmountField);
    
        JButton submitButton = createStyledButton("Make Payment");
        submitButton.addActionListener(e -> {
            String loanId = loanIdField.getText();
            try {
                double paymentAmount = Double.parseDouble(paymentAmountField.getText());
                for (Customer customer : customers) {
                    for (Loan loan : customer.getLoans()) {
                        if (loan.getLoanInfo().equals(loanId)) {
                            if (loan.isApproved()) {
                                loan.makePayment(paymentAmount);
                                outputArea.setText("Payment of R" + String.format("%.2f", paymentAmount) + 
                                                   " made to loan " + loanId + 
                                                   "\nRemaining balance: R" + String.format("%.2f", loan.getRemainingBalance()));
                            } else {
                                outputArea.setText("Loan " + loanId + " is not approved. Payment cannot be made.");
                            }
                            dialog.dispose();
                            return;
                        }
                    }
                }
                outputArea.setText("Loan not found.");
            } catch (NumberFormatException ex) {
                outputArea.setText("Invalid payment amount. Please enter a valid number.");
            }
            dialog.dispose();
        });
    
        dialog.add(submitButton);
    
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void openNewAccount() {
        JDialog dialog = new JDialog(this, "Open New Account", true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(400, 200);

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        inputPanel.add(new JLabel("Customer ID:"));
        JTextField customerIdField = new JTextField();
        inputPanel.add(customerIdField);

        inputPanel.add(new JLabel("Initial Balance:"));
        JTextField balanceField = new JTextField();
        inputPanel.add(balanceField);

        JButton submitButton = createStyledButton("Open Account");
        submitButton.addActionListener(e -> {
            String customerId = customerIdField.getText();
            double initialBalance = Double.parseDouble(balanceField.getText());

            Customer customer = findCustomerById(customerId);
            if (customer != null) {
                String accountNumber = "A" + (int) (Math.random() * 10000);
                Account newAccount = new Account(accountNumber, initialBalance) {
                    @Override
                    public void deposit(double amount) throws InvalidAmountException {

                    }

                    @Override
                    public void withdraw(double amount) throws InsufficientFundsException, InvalidAmountException {

                    }

                    @Override
                    public String getAccountInfo() {
                        return "";
                    }
                };
                customer.addAccount(newAccount);
                outputArea.setText("New account opened successfully!\nAccount Number: " + accountNumber);
            } else {
                outputArea.setText("Customer not found.");
            }
            dialog.dispose();
        });

        dialog.add(inputPanel, BorderLayout.CENTER);
        dialog.add(submitButton, BorderLayout.SOUTH);

        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void depositToAccount() {
        JDialog dialog = new JDialog(this, "Deposit to Account", true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(400, 200);
    
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    
        inputPanel.add(new JLabel("Account Number:"));
        JTextField accountNumberField = new JTextField();
        inputPanel.add(accountNumberField);
    
        inputPanel.add(new JLabel("Deposit Amount:"));
        JTextField amountField = new JTextField();
        inputPanel.add(amountField);
    
        JButton submitButton = createStyledButton("Deposit");
        submitButton.addActionListener(e -> {
            String accountNumber = accountNumberField.getText();
            double amount = Double.parseDouble(amountField.getText());
    
            Account account = findAccountByNumber(accountNumber);
            if (account != null) {
                try {
                    account.deposit(amount);
                    outputArea.setText("Deposit successful.\nNew balance: R" + account.getBalance());
                } catch (InvalidAmountException ex) {
                    outputArea.setText("Error: " + ex.getMessage());
                }
            } else {
                outputArea.setText("Account not found.");
            }
            dialog.dispose();
        });
    
        dialog.add(inputPanel, BorderLayout.CENTER);
        dialog.add(submitButton, BorderLayout.SOUTH);
    
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
    
    private void withdrawFromAccount() {
        JDialog dialog = new JDialog(this, "Withdraw from Account", true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(400, 200);
    
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    
        inputPanel.add(new JLabel("Account Number:"));
        JTextField accountNumberField = new JTextField();
        inputPanel.add(accountNumberField);
    
        inputPanel.add(new JLabel("Withdrawal Amount:"));
        JTextField amountField = new JTextField();
        inputPanel.add(amountField);
    
        JButton submitButton = createStyledButton("Withdraw");
        submitButton.addActionListener(e -> {
            String accountNumber = accountNumberField.getText();
            double amount = Double.parseDouble(amountField.getText());
    
            Account account = findAccountByNumber(accountNumber);
            if (account != null) {
                try {
                    account.withdraw(amount);
                    outputArea.setText("Withdrawal successful.\nNew balance: R" + account.getBalance());
                } catch (InsufficientFundsException | InvalidAmountException ex) {
                    outputArea.setText("Error: " + ex.getMessage());
                }
            } else {
                outputArea.setText("Account not found.");
            }
            dialog.dispose();
        });
    
        dialog.add(inputPanel, BorderLayout.CENTER);
        dialog.add(submitButton, BorderLayout.SOUTH);
    
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
    
    private void viewAccountBalance() {
        JDialog dialog = new JDialog(this, "View Account Balance", true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(400, 150);
    
        JPanel inputPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    
        inputPanel.add(new JLabel("Account Number:"));
        JTextField accountNumberField = new JTextField();
        inputPanel.add(accountNumberField);
    
        JButton submitButton = createStyledButton("View Balance");
        submitButton.addActionListener(e -> {
            String accountNumber = accountNumberField.getText();
    
            Account account = findAccountByNumber(accountNumber);
            if (account != null) {
                outputArea.setText("Account Balance: R" + account.getBalance() + "\n" + account.getAccountInfo());
            } else {
                outputArea.setText("Account not found.");
            }
            dialog.dispose();
        });
    
        dialog.add(inputPanel, BorderLayout.CENTER);
        dialog.add(submitButton, BorderLayout.SOUTH);
    
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
    
    private Account findAccountByNumber(String accountNumber) {
        for (Customer customer : customers) {
            for (Account account : customer.getAccounts()) {
                if (account.getAccountNumber().equals(accountNumber)) {
                    return account;
                }
            }
        }
        return null;
    }

    private Customer findCustomerById(String customerId) {
        for (Customer customer : customers) {
            if (customer.getId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }

    private void addNewCustomer() {
        JDialog dialog = new JDialog(this, "Add New Customer", true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(400, 300);

        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        String[] labels = {"Name:", "ID:", "Street:", "City:", "State:", "Zip Code:"};
        JTextField[] fields = new JTextField[labels.length];

        for (int i = 0; i < labels.length; i++) {
            inputPanel.add(new JLabel(labels[i]));
            fields[i] = new JTextField();
            inputPanel.add(fields[i]);
        }

        JButton submitButton = createStyledButton("Submit");
        submitButton.addActionListener(e -> {
            String name = fields[0].getText();
            String id = fields[1].getText();
            String street = fields[2].getText();
            String city = fields[3].getText();
            String state = fields[4].getText();
            String zipCode = fields[5].getText();

            Address address = new Address(street, city, state, zipCode);
            Customer newCustomer = new Customer(name, id, address);
            customers.add(newCustomer);

            outputArea.setText("New customer added successfully!\n" + newCustomer.getDetails());
            dialog.dispose();
        });

        dialog.add(inputPanel, BorderLayout.CENTER);
        dialog.add(submitButton, BorderLayout.SOUTH);

        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void viewCustomerDetails() {
        JDialog dialog = new JDialog(this, "View Customer Details", true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(400, 150);
    
        JPanel inputPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    
        inputPanel.add(new JLabel("Customer ID:"));
        JTextField customerIdField = new JTextField();
        inputPanel.add(customerIdField);
    
        JButton submitButton = createStyledButton("View Details");
        submitButton.addActionListener(e -> {
            String customerId = customerIdField.getText();
            Customer customer = findCustomerById(customerId);
            if (customer != null) {
                outputArea.setText(customer.getDetails() + "\n" + customer.getAccountsAndLoans());
            } else {
                outputArea.setText("Customer not found.");
            }
            dialog.dispose();
        });
    
        dialog.add(inputPanel, BorderLayout.CENTER);
        dialog.add(submitButton, BorderLayout.SOUTH);
    
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
    
    private void updateCustomerAddress() {
        JDialog dialog = new JDialog(this, "Update Customer Address", true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(400, 300);
    
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    
        String[] labels = {"Customer ID:", "Street:", "City:", "State:", "Zip Code:"};
        JTextField[] fields = new JTextField[labels.length];
    
        for (int i = 0; i < labels.length; i++) {
            inputPanel.add(new JLabel(labels[i]));
            fields[i] = new JTextField();
            inputPanel.add(fields[i]);
        }
    
        JButton submitButton = createStyledButton("Update Address");
        submitButton.addActionListener(e -> {
            String customerId = fields[0].getText();
            String street = fields[1].getText();
            String city = fields[2].getText();
            String state = fields[3].getText();
            String zipCode = fields[4].getText();
    
            Customer customer = findCustomerById(customerId);
            if (customer != null) {
                Address newAddress = new Address(street, city, state, zipCode);
                customer.setAddress(newAddress);
                outputArea.setText("Customer address updated successfully!\n" + customer.getDetails());
            } else {
                outputArea.setText("Customer not found.");
            }
            dialog.dispose();
        });
    
        dialog.add(inputPanel, BorderLayout.CENTER);
        dialog.add(submitButton, BorderLayout.SOUTH);
    
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            BankAccountManagementGUI gui = new BankAccountManagementGUI();
            gui.setVisible(true);
        });
    }
}