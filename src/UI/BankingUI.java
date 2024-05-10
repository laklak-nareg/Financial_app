package UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankingUI extends JFrame {
    private double savingsBalance = 1000;
    private double checkingBalance = 500;

    private JLabel savingsLabel, checkingLabel;
    private JTextField amountField;
    private JButton depositButton, withdrawButton, transferButton;
    private JComboBox<String> accountSelectionBox;

    public BankingUI() {
        setTitle("Banking Application");
        setSize(400, 210);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);


        savingsLabel = new JLabel("Savings: $" + savingsBalance);
        savingsLabel.setBounds(50, 20, 300, 25);
        add(savingsLabel);

        checkingLabel = new JLabel("Checking: $" + checkingBalance);
        checkingLabel.setBounds(50, 50, 200, 25);
        add(checkingLabel);


//        accountSelectionBox = new JComboBox<>(new String[]{"Savings", "Checking"});
//        accountSelectionBox.setBounds(40, 80, 120, 25);
//        add(accountSelectionBox);


        depositButton = new JButton("Deposit");
        depositButton.setBounds(280, 80, 100, 25);
        add(depositButton);


        withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(280, 110, 100, 25);
        add(withdrawButton);

        transferButton = new JButton("Transfer to Other");
        transferButton.setBounds(40, 140, 210, 25);
        add(transferButton);


        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DepositUI(new double[]{savingsBalance, checkingBalance}, savingsLabel, checkingLabel);
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WithdrawUI(new double[]{savingsBalance, checkingBalance}, savingsLabel, checkingLabel);
            }
        });

        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TransferUI(new double[]{savingsBalance, checkingBalance}, savingsLabel, checkingLabel);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new BankingUI();
    }
}
