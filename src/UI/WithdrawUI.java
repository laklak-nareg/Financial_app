package UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class WithdrawUI extends JFrame {
    private JComboBox<String> accountBox;
    private JTextField withdrawAmountField;
    private JButton withdrawButton;

    public WithdrawUI(double[] balances, JLabel savingsLabel, JLabel checkingLabel) {
        setTitle("Withdraw Funds");
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

        withdrawAmountField = new JTextField();
        withdrawAmountField.setBounds(100, 50, 150, 25);
        add(withdrawAmountField);

        withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(100, 80, 150, 25);
        add(withdrawButton);

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(withdrawAmountField.getText());
                int accountIndex = accountBox.getSelectedIndex();
                if (balances[accountIndex] >= amount) {
                    balances[accountIndex] -= amount;
                    if (accountIndex == 0) {
                        savingsLabel.setText("Savings: $" + balances[0]);
                    } else {
                        checkingLabel.setText("Checking: $" + balances[1]);
                    }
                    JOptionPane.showMessageDialog(null, "Withdrawal Successful!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Insufficient Funds!");
                }
            }
        });

        setVisible(true);
    }
}

