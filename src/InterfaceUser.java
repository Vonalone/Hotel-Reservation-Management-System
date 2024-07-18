import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.io.*;
import java.sql.*;

public class InterfaceUser extends JFrame implements ActionListener {
    private JButton btnDelete = new JButton("Delete");

    private static final String[] columns = {"user_id", "first_name", "last_name", "email", "cin", "phone_number"};
    private DefaultTableModel model = new DefaultTableModel(columns, 0);
    private JTable table = new JTable(model);

    public InterfaceUser() {
        setTitle("User Management");

        try {
            ResultSet resultSet = user.displayAllUser();
            while (resultSet.next()) {
                model.addRow(new Object[]{resultSet.getInt("user_id"), resultSet.getString("first_name"), resultSet.getString("last_name"), resultSet.getString("email"), resultSet.getString("cin"), resultSet.getString("phone_number")});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error fetching users: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(btnDelete, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setVisible(true);

        btnDelete.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnDelete) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String cinUser = (String) model.getValueAt(selectedRow, 4);
                model.removeRow(selectedRow);
                user.deleteUser(cinUser);
                JOptionPane.showMessageDialog(this, "User deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to delete.", "No Row Selected", JOptionPane.WARNING_MESSAGE);
            }
        }
    }


}
