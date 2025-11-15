package PasswordValidator;

import javax.swing.*;
import java.awt.*;

public class PasswordValidatorGUI extends JFrame {
    private JPanel contentPanel;
    private JLabel statusLabel;
    private JTextField passwordTextField;

    private boolean passwordValidator(String input) {
        statusLabel.setVisible(true);

        if (input.length() < 8) {
            statusLabel.setText("Password must be 8 characters long");
            return false;
        }

        boolean hasDigit = false;
        boolean hasSpecialCharacter = false;
        for (char c : input.toCharArray()) {
            if (Character.isDigit(c)) {
                hasDigit = true;
            }

            if (!Character.isDigit(c) && !Character.isLetter(c)) {
                hasSpecialCharacter = true;
            }
        }

        if (hasDigit && !hasSpecialCharacter) {
            statusLabel.setText("Password must contain at least one special character");
            return false;
        } else if (!hasDigit && hasSpecialCharacter || !hasDigit && !hasSpecialCharacter) {
            statusLabel.setText("Passowrd must contain at least one digit");
            return false;
        }

        statusLabel.setText("Password is valid");
        return true;
    }

    private void implementation() {
        passwordTextField.addActionListener(e -> {
            System.out.println("User pressed enter");

            if (passwordValidator(passwordTextField.getText()))  {
                statusLabel.setForeground(Color.GREEN);
            } else {
                statusLabel.setForeground(Color.RED);
            }
        });
    }

    private void setName() {
        statusLabel.setName("statusLabel");
        passwordTextField.setName("passwordTextField");
    }

    public PasswordValidatorGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Password Validator");
        add(contentPanel);
        setResizable(false);
        setPreferredSize(new Dimension(500, 250));

        setName();
        implementation();

        pack();

        setVisible(true);
    }
}
