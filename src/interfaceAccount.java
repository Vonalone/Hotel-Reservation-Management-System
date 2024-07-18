import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class interfaceAccount extends JFrame {
    private JLabel nameLabel, emailLabel, cinLabel, phoneLabel, passwordLabel;
    private JTextField nameField, emailField, cinField, phoneField;
    private JPasswordField passwordField;
    private JButton changePasswordButton, logoutButton, deleteAccountButton;

    public interfaceAccount(String name, String email, String cin, String phoneNumber) {
        setTitle("User Account Information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 350);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridLayout(7, 2, 10, 10));

        nameLabel = new JLabel("Name:");
        emailLabel = new JLabel("Email:");
        cinLabel = new JLabel("CIN:");
        phoneLabel = new JLabel("Phone Number:");
        passwordLabel = new JLabel("Password:");

        nameField = new JTextField(name);
        emailField = new JTextField(email);
        cinField = new JTextField(cin);
        phoneField = new JTextField(phoneNumber);
        passwordField = new JPasswordField();

        nameField.setEditable(false);
        emailField.setEditable(false);
        cinField.setEditable(false);
        phoneField.setEditable(false);

        changePasswordButton = new JButton("Modify Password");
        logoutButton = new JButton("Logout");
        deleteAccountButton = new JButton("Delete Account");

        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String passwordNew = new String(passwordField.getPassword());
                String cin = cinField.getText();
                user u = new user(cin);
                if (!passwordNew.isEmpty()) {
                    u.changePassword(passwordNew);
                    JOptionPane.showMessageDialog(interfaceAccount.this, "Password modification successful!");
                } else {
                    JOptionPane.showMessageDialog(interfaceAccount.this, "Empty Champs !");
                }
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new login("login page").setVisible(true);
            }
        });

        deleteAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(interfaceAccount.this, "Are you sure you want to delete your account?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    String cin = cinField.getText();
                    user.deleteUser(cin);
                    JOptionPane.showMessageDialog(interfaceAccount.this, "Account deleted successfully.");
                    dispose();
                    new login("login page").setVisible(true);
                }
            }
        });

        mainPanel.add(nameLabel);
        mainPanel.add(nameField);
        mainPanel.add(emailLabel);
        mainPanel.add(emailField);
        mainPanel.add(cinLabel);
        mainPanel.add(cinField);
        mainPanel.add(phoneLabel);
        mainPanel.add(phoneField);
        mainPanel.add(passwordLabel);
        mainPanel.add(passwordField);

        mainPanel.add(changePasswordButton);
        mainPanel.add(logoutButton);
        mainPanel.add(deleteAccountButton);

        add(mainPanel);
        setVisible(true);
    }
}
