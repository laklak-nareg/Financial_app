package core;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ArrayList;

public class Bank {
    private static User adminUser = null;

    private Map<String, Account> accounts = new HashMap<>();
    private Map<String, User> users = new HashMap<>();
    private ArrayList<TransferRequest> transferRequests = new ArrayList<>();

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

    public void requestTransfer(String userID, String sourceAccountNumber, String destinationAccountNumber, double amount) {
        User user = users.get(userID);
        if(user != null && accounts.containsKey(sourceAccountNumber) && accounts.containsKey(destinationAccountNumber)){
            if(amount > 0) {
                TransferRequest newRequest = new TransferRequest(sourceAccountNumber, destinationAccountNumber, amount);
                transferRequests.add(newRequest);
                System.out.println("Transfer request has been submitted for " + amount + " from account " + sourceAccountNumber + " to account " + destinationAccountNumber);
            } else {
                System.out.println("invalid amount for transfer, can not be negative");
            }
        } else {
                System.out.println("Transfer request failed, double check the account numbers and the amount");

        }
    }

    public void processTransferRequests(String adminUserId) {
        User user = users.get(adminUserId);
        if(user != null && "admin".equals(user.getRole())) {
            Iterator<TransferRequest> iterator = transferRequests.iterator();
            while(iterator.hasNext()) {
                TransferRequest request = iterator.next();
                if(!request.isApproved()) {
                    System.out.println(" Processing transfer request from " + request.getSourceAccountNumber()+ " to " + request.getDestinationAccountNumber());
                    Account sourceAccount = accounts.get(request.getSourceAccountNumber());
                    Account destinationAccount = accounts.get(request.getDestinationAccountNumber());
                    if(sourceAccount != null && destinationAccount != null && sourceAccount.getBalance() >= request.getAmount()){
                        // Calling adminAdjustBalance on both accounts
                        sourceAccount.adminAdjustBalance(adminUserId, -request.getAmount(),users);
                        destinationAccount.adminAdjustBalance(adminUserId,request.getAmount(),users);
                        request.setApproved(true);
                        System.out.println("Transfer of: " + request.getAmount() + " from " + request.getSourceAccountNumber() + " to "+ request.getDestinationAccountNumber() + " has been approved and processed");
                        iterator.remove();
                    } else {
                        if(sourceAccount != null && sourceAccount.getBalance()<request.getAmount()){
                            System.out.println("Insufficient funds for transfer from " + request.getSourceAccountNumber());
                        } else {
                            System.out.println(" one of the accounts is not valid ");
                        }
                    }
                }
            }
        } else {
            System.out.println(" only admin can process transfer requests");
        }
    }
}
