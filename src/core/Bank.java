package core;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class Bank {
    private static User adminUser = null;

    private Map<String, Account> accounts = new HashMap<>();
    private Map<String, User> users = new HashMap<>();

    public void createAccount(String userId, String accountNumber, String accountHolder, double initialBalance, String accountType) {
        User user = users.get(userId);
        if( user !=null && "admin".equals(user.getRole())){
            Account newAccount = new Account(accountNumber, accountHolder, initialBalance, accountType);
            accounts.put(accountNumber, newAccount);
            System.out.println("Account created successfully");
        } else {
            System.out.println("only admin can create accounts");
        }
    }

    public void modifyBalance(String userId, String accountNumber, double balance) {
        Account account = accounts.get(accountNumber);
        if(account != null){
            account.adminAdjustBalance(userId,balance,users);
        } else {
            System.out.println("account number not found");
        }
    }

    public User createAdminUser(String username, String password) {
        if(adminUser == null) {
            adminUser = new User(username,password,"admin");
            users.put(username,adminUser);

            System.out.println("Admin user has been created successfully ");
            return adminUser;
        } else {
            System.out.println("admin user already exists, can not create another admin user");
            return null;
        }
    }

    public double getAccountBalance(String accountNumber) {
        Account account = accounts.get(accountNumber);
        if(account != null ){
            return account.getBalance();
        } else {
            System.out.println("account not found");
            return -1;
        }
    }

    public void registerUser(User user) {
        users.put(user.getUsername(), user);
    }
}
