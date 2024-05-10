package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, forgotPasswordButton, registerButton;

    public LoginScreen() {
        setTitle("Login Screen");
        setSize(300, 180);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 20, 80, 25);
        add(userLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(100, 20, 165, 25);
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(100, 50, 165, 25);
        add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 120, 25);
        add(loginButton);

        forgotPasswordButton = new JButton("Forgot Password?");
        forgotPasswordButton.setBounds(10, 110, 150, 25);
        add(forgotPasswordButton);

        registerButton = new JButton("Register");
        registerButton.setBounds(160, 110, 120, 25);
        add(registerButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authenticateLogin();
            }
        });

        forgotPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openForgotPasswordDialog();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openRegistrationDialog();
            }
        });

        setVisible(true);
    }

    private void authenticateLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if ("Gevorg".equals(username) && "12345".equals(password)) {
            JOptionPane.showMessageDialog(this, "Login Successful!");
            this.dispose();
            new BankingUI();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.");
        }
    }

    private void openForgotPasswordDialog() {
        JDialog forgotDialog = new JDialog(this, "Forgot Password", true);
        forgotDialog.setLayout(new FlowLayout());
        forgotDialog.add(new JLabel("Please contact bank@domain.com to reset your password."));
        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> forgotDialog.dispose());
        forgotDialog.add(okButton);
        forgotDialog.setSize(450, 90);
        forgotDialog.setVisible(true);
    }

    private void openRegistrationDialog() {
        JDialog registerDialog = new JDialog(this, "Registration", true);
        registerDialog.setLayout(new FlowLayout());
        registerDialog.add(new JLabel("For registration please visit one our branches with your passport, or ID card."));
        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> registerDialog.dispose());
        registerDialog.add(okButton);
        registerDialog.setSize(550, 90);
        registerDialog.setVisible(true);
    }

    public static void main(String[] args) {
        new LoginScreen();
    }
}
