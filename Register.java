package medicare;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Register extends JFrame implements ActionListener {

    JTextField nameField, idField, passwordField;
    JButton registerButton, cancelButton;

    Register() {
        // Name Label
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(40, 20, 100, 30);
        nameLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(150, 20, 150, 30);
        nameField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(nameField);

        // Username Label
        JLabel idLabel = new JLabel("Username:");
        idLabel.setBounds(40, 70, 100, 30);
        idLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(idLabel);

        idField = new JTextField();
        idField.setBounds(150, 70, 150, 30);
        idField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(idField);

        // Password Label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(40, 120, 100, 30);
        passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(passwordLabel);

        passwordField = new JTextField();
        passwordField.setBounds(150, 120, 150, 30);
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(passwordField);

        // Register Button
        registerButton = new JButton("Register");
        registerButton.setBounds(40, 180, 120, 30);
        registerButton.setFont(new Font("serif", Font.BOLD, 15));
        registerButton.setBackground(Color.BLACK);
        registerButton.setForeground(Color.white);
        registerButton.addActionListener(this);
        add(registerButton);

        // Cancel Button
        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(180, 180, 120, 30);
        cancelButton.setFont(new Font("serif", Font.BOLD, 15));
        cancelButton.setBackground(Color.BLACK);
        cancelButton.setForeground(Color.white);
        cancelButton.addActionListener(this);
        add(cancelButton);

        // Window settings
        getContentPane().setBackground(new Color(109, 164, 170));
        setSize(400, 300);
        setLocation(500, 300);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) { // Handle registration
            try {
                conn c = new conn();
                String name = nameField.getText();
                String id = idField.getText();
                String password = passwordField.getText();

                // SQL query to insert the new user into the database
                String query = "INSERT INTO login (Name, ID, PW) VALUES ('" + name + "', '" + id + "', '" + password + "')";
                c.statement.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "User Registered Successfully");
                new Login(); // Show the login screen after successful registration
                setVisible(false); // Close the register screen
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == cancelButton) { // Cancel and return to login
            new Login(); // Go back to Login
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Register();
    }
}
