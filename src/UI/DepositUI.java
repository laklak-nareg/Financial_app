package UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DepositUI extends JFrame {
    private JComboBox<String> accountBox;
    private JTextField depositAmountField;
    private JButton depositButton;

    public DepositUI(double[] balances, JLabel savingsLabel, JLabel checkingLabel) {
        setTitle("Deposit Funds");
        setSize(300, 150);
        setLayout(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        JLabel accountLabel = new JLabel("Account:");
        accountLabel.setBounds(10, 20, 80, 25);
        add(accountLabel);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(10, 50, 80, 25);
        add(amountLabel);

        accountBox = new JComboBox<>(new String[]{"Savings", "Checking"});
        accountBox.setBounds(100, 20, 150, 25);
        add(accountBox);

        depositAmountField = new JTextField();
        depositAmountField.setBounds(100, 50, 150, 25);
        add(depositAmountField);

        depositButton = new JButton("Deposit");
        depositButton.setBounds(100, 80, 150, 25);
        add(depositButton);

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(depositAmountField.getText());
                int accountIndex = accountBox.getSelectedIndex();
                balances[accountIndex] += amount;
                if (accountIndex == 0) {
                    savingsLabel.setText("Savings: $" + balances[0]);
                } else {
                    checkingLabel.setText("Checking: $" + balances[1]);
                }
                JOptionPane.showMessageDialog(null, "Deposit Successful!");
                dispose();
            }
        });

        setVisible(true);
    }
}