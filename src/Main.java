//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.


import core.Bank;
import core.User;

public class Main {
    public static void main(String[] args) {
        System.out.println(" Lets make a bank Y'all    ?");
        Bank bank = new Bank();
        User admin = bank.createAdminUser("admin", "password");
        if (admin != null) {
            // Using admin username to create accounts
            bank.createAccount("admin", "0001", "nareg", 6750, "savings");
            bank.createAccount("admin", "0002", "admin", 100000, "checking");

//
            // Modifying balance
            bank.modifyBalance("admin", "0001", 200);  // example of adding balance
            System.out.println("Account Balance for 0001: " + bank.getAccountBalance("0001"));

            User nonAdminUser = new User("user1", "user1pass", "user");
            bank.registerUser(nonAdminUser);

//
            // Trying to modify balance as non-admin
            bank.modifyBalance("user1", "0001", 500);
            System.out.println("Attempted Account Balance for 0001 by non-admin: " + bank.getAccountBalance("0001"));
            bank.modifyBalance("user1", "0002", 500);  // Non-admin attempt
            System.out.println("Attempted Account Balance for 0001 by non-admin: " + bank.getAccountBalance("0002"));


            // Simulating transfer request ---  Request to transfer from 0001 to 0002
            bank.requestTransfer("admin", "0001", "0002", 500);


            bank.processTransferRequests("admin");
            System.out.println("Post-transfer Account Balance for 0001: " + bank.getAccountBalance("0001"));
            System.out.println("Post-transfer Account Balance for 0002: " + bank.getAccountBalance("0002"));


        }
    }
}