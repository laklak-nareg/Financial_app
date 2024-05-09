package core;

import java.util.Map;

public abstract class Account {

    private String accountNumber;
    private String accountHolder;
    private double balance;
    private String accountType;

    private void adjustBalance(double amount){
        balance += amount;
    }

    public void adminAdjustBalance(String userId, double amount, Map<String, User> users){
        User user = users.get(userId);
        if(user != null && "admin".equals(user.getRole())) {
            adjustBalance(amount);
            System.out.println("Balance been adjusted by admin. New Balance: " + this.balance);
        } else {
            if (user == null) {
                System.out.println("User ID not found");
            } else {
                System.out.println("admin can modify balance only");
            }
        }
    }

    // constructor to initialize an account
    public Account(String accountNumber, String accountHolder, double initialBalance, String accountType){
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
        this.accountType = accountType;
    }

    // deposit method
    public void deposit(double amount) {
        if(amount > 0) {
            adjustBalance(amount);
            System.out.println("Deposit is successful, the new balance is: " + balance);
        } else {
            System.out.println("Invalid amount, Enter a positive number");
        }
    }

    // withdraw method
    public void withdraw(double amount) {
        if (amount > 0 && balance >= amount){
            adjustBalance(-amount);
            System.out.println("Withdraw successful, the updated amount left in balance is: " + balance);
        } else if (amount <= 0) {
            System.out.println("Invalid amount, enter a positive amount");
        } else {
            System.out.println("Insufficient funds");
        }
    }

    protected abstract void applyInterest();

    // getters and setters
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public static class SavingsAccount extends Account {
        private static final double INTEREST_RATE = 0.05;

        public SavingsAccount(String accountNumber, String accountHolder, double initialBalance) {
            super(accountNumber, accountHolder, initialBalance, "savings");
        }

        @Override
        protected void applyInterest() {
            double interest = getBalance() * INTEREST_RATE;
            super.deposit(interest);
            System.out.println("Interest applied to Savings Account: " + interest);
        }
    }

    public static class CheckingAccount extends Account {
        private static final double INTEREST_RATE = 0.01;

        public CheckingAccount(String accountNumber, String accountHolder, double initialBalance) {
            super(accountNumber, accountHolder, initialBalance, "checking");
        }

        @Override
        protected void applyInterest() {
            double interest = getBalance() * INTEREST_RATE;
            super.deposit(interest);
            System.out.println("Interest applied to Checking Account: " + interest);
        }
    }
}
