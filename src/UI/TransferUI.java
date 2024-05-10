package UI;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

public class TransferUI extends JFrame {
    private JComboBox<String> sourceAccountBox;
    private JTextField transferAmountField, cardNumberField;
    private JButton transferButton;

    public TransferUI(double[] balances, JLabel savingsLabel, JLabel checkingLabel) {
        setTitle("Transfer Funds");
        setSize(285, 180);
        setLayout(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        JLabel sourceLabel = new JLabel("From Account:");
        sourceLabel.setBounds(10, 20, 100, 25);
        add(sourceLabel);

        sourceAccountBox = new JComboBox<>(new String[]{"Savings", "Checking"});
        sourceAccountBox.setBounds(120, 20, 150, 25);
        add(sourceAccountBox);

        JLabel destinationLabel = new JLabel("To Card Number:");
        destinationLabel.setBounds(10, 50, 130, 25);
        add(destinationLabel);

        cardNumberField = new JTextField();
        cardNumberField.setBounds(120, 50, 150, 25);
        add(cardNumberField);
        ((PlainDocument) cardNumberField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                String originalText = fb.getDocument().getText(0, fb.getDocument().getLength());
                String newString = originalText.substring(0, offset) + text + originalText.substring(offset);
                newString = newString.replaceAll("\\s+", "");
                if (newString.length() <= 16) {
                    super.replace(fb, 0, fb.getDocument().getLength(), formatCardNumber(newString), attrs);
                }
            }
        });

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(10, 80, 100, 25);
        add(amountLabel);

        transferAmountField = new JTextField();
        transferAmountField.setBounds(120, 80, 150, 25);
        add(transferAmountField);

        transferButton = new JButton("Transfer");
        transferButton.setBounds(120, 110, 150, 25);
        add(transferButton);

        transferButton.addActionListener(e -> {
            performTransfer(balances, savingsLabel, checkingLabel);
        });

        setVisible(true);
    }

    private void performTransfer(double[] balances, JLabel savingsLabel, JLabel checkingLabel) {
        double amount = Double.parseDouble(transferAmountField.getText());
        int sourceIndex = sourceAccountBox.getSelectedIndex();
        if (balances[sourceIndex] >= amount) {
            balances[sourceIndex] -= amount;
            savingsLabel.setText("Savings: $" + balances[0]);
            checkingLabel.setText("Checking: $" + balances[1]);
            JOptionPane.showMessageDialog(null, "Transfer of $" + amount + " to card number " + cardNumberField.getText() + " was successful!");
        } else {
            JOptionPane.showMessageDialog(null, "Insufficient funds!");
        }
    }

    private String formatCardNumber(String text) {
        StringBuilder formatted = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (i > 0 && i % 4 == 0) {
                formatted.append(" ");
            }
            formatted.append(text.charAt(i));
        }
        return formatted.toString();
    }
}
