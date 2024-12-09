package medicare;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

    JTextField textField;
    JPasswordField jPasswordField;
    JButton b1, b2, b3, b4;
    JLabel label;

    Login() {

        // Username Label
        JLabel namelabel = new JLabel("Username");
        namelabel.setBounds(40, 20, 100, 30);
        namelabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        namelabel.setForeground(Color.BLACK);
        add(namelabel);

        // Password Label
        JLabel password = new JLabel("Password");
        password.setBounds(40, 70, 100, 30);
        password.setFont(new Font("Tahoma", Font.BOLD, 16));
        password.setForeground(Color.BLACK);
        add(password);

        // Username TextField
        textField = new JTextField();
        textField.setBounds(150, 20, 150, 30);
        textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField.setBackground(new Color(255, 179, 0));
        add(textField);

        // Password Field
        jPasswordField = new JPasswordField();
        jPasswordField.setBounds(150, 70, 150, 30);
        jPasswordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        jPasswordField.setBackground(new Color(255, 179, 0));
        add(jPasswordField);

        // Login Button
        b1 = new JButton("Login");
        b1.setBounds(40, 140, 120, 30);
        b1.setFont(new Font("serif", Font.BOLD, 15));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.white);
        b1.addActionListener(this);
        add(b1);

        // Cancel Button
        b2 = new JButton("Cancel");
        b2.setBounds(180, 140, 120, 30);
        b2.setFont(new Font("serif", Font.BOLD, 15));
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.white);
        b2.addActionListener(this);
        add(b2);

        // Forgot Password Button
        b3 = new JButton("Forgot Password");
        b3.setBounds(40, 180, 150, 30);
        b3.setFont(new Font("serif", Font.PLAIN, 12));
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.white);
        b3.addActionListener(this);
        add(b3);

        // Register Button
        b4 = new JButton("Register New User");
        b4.setBounds(200, 180, 150, 30);
        b4.setFont(new Font("serif", Font.PLAIN, 12));
        b4.setBackground(Color.BLACK);
        b4.setForeground(Color.white);
        b4.addActionListener(this);
        add(b4);

        getContentPane().setBackground(new Color(109, 164, 170));
        setSize(750, 300);
        setLocation(400, 270);
        setLayout(null);
        setVisible(true);

        // Call resizeImage after window has been displayed
        resizeImage();
    }

    private void resizeImage() {
        // Image Icon with dynamic resize
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/login.jpg"));

        // Use current frame size to scale the image
        int imageWidth = getWidth();  // Get current width after the JFrame is displayed
        int imageHeight = getHeight(); // Get current height after the JFrame is displayed

        // Check if dimensions are non-zero to avoid scaling errors
        if (imageWidth > 0 && imageHeight > 0) {
            Image i1 = imageIcon.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
            ImageIcon imageIcon1 = new ImageIcon(i1);

            label = new JLabel(imageIcon1);
            label.setBounds(200,-20, imageWidth, imageHeight); // Adjust based on the window size
            add(label);
            repaint();  // Repaint after the label is added
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) { // Login
            try {
                conn c = new conn();
                String user = textField.getText();
                String Pass = new String(jPasswordField.getPassword());

                String q = "SELECT * FROM login WHERE ID = '" + user + "' AND PW = '" + Pass + "'";
                ResultSet resultSet = c.statement.executeQuery(q);

                if (resultSet.next()) {
                    new Reception();  // Open Reception window
                    setVisible(false);  // Close login window
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Credentials");
                }

            } catch (Exception E) {
                E.printStackTrace();
            }

        } else if (e.getSource() == b2) { // Cancel
            System.exit(0);
        } else if (e.getSource() == b3) { // Forgot Password
            String user = textField.getText();
            if (!user.isEmpty()) {
                try {
                    conn c = new conn();
                    String query = "SELECT * FROM login WHERE ID = '" + user + "'";
                    ResultSet rs = c.statement.executeQuery(query);
                    if (rs.next()) {
                        String newPassword = JOptionPane.showInputDialog("Enter new password: ");
                        String updateQuery = "UPDATE login SET PW = '" + newPassword + "' WHERE ID = '" + user + "'";
                        c.statement.executeUpdate(updateQuery);
                        JOptionPane.showMessageDialog(null, "Password Updated Successfully");
                    } else {
                        JOptionPane.showMessageDialog(null, "Username not found");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a username");
            }
        } else if (e.getSource() == b4) { // Register New User
            new Register();  // Open Register window
            setVisible(false);  // Close login window
        }
    }

    public static void main(String[] args) {
        new Login();  // Launch the login window
    }
}
