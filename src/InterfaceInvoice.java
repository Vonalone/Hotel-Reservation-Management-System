import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.io.*;
import java.sql.*;

public class InterfaceInvoice extends JFrame implements ActionListener {
    private JButton btnDelete = new JButton("Delete");

    private static final String[] columns = {"invoice_id", "invoice_user_id", "price", "payment_date"};
    private DefaultTableModel model = new DefaultTableModel(columns, 0);
    private JTable table = new JTable(model);

    public InterfaceInvoice(String cin) {
        setTitle("Invoice Management");

        try {
            ResultSet resultSet;
            user u =new user(cin);
            if(!u.is_admin()){
                resultSet = invoice.DisplayInvoiceUser(u.userId());
            }else{
                resultSet = invoice.DisplayInvoice();}
            while (resultSet.next()) {
                model.addRow(new Object[]{resultSet.getInt("invoice_id"), resultSet.getInt("invoice_user_id"), resultSet.getDouble("price"), resultSet.getDate("payment_date")});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error fetching invoices: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
                int invoiceId = (int) model.getValueAt(selectedRow, 0);
                model.removeRow(selectedRow);
                invoice.deleteInvoice(invoiceId);
                JOptionPane.showMessageDialog(this, "Invoice deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to delete.", "No Row Selected", JOptionPane.WARNING_MESSAGE);
            }
        }
    }


}
