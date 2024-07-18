import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.io.*;
import java.sql.*;

public class InterfaceReserv extends JFrame implements ActionListener {
    private JButton btn = new JButton("Delete");

    private static final String[] columns = {"reserv_id", "user_reserv_id", "room_reserv_id", "check_in_date", "check_out_date", "person_number"};
    private DefaultTableModel model = new DefaultTableModel(columns, 0);
    private JTable table = new JTable(model);

    public InterfaceReserv(String cin) {
        setTitle("RESERVATION Management");

        try {
            ResultSet resultSet;
            user u =new user(cin);
            if(!u.is_admin()){
            resultSet = reservation.displayUserReservations(u.userId());
            }else{
            resultSet = reservation.displayAllReservations();}
            while (resultSet.next()) {
                model.addRow(new Object[]{resultSet.getInt("reserv_id"), resultSet.getInt("user_reserv_id"), resultSet.getInt("room_reserv_id"), resultSet.getDate("check_in_date"), resultSet.getDate("check_out_date"), resultSet.getInt("person_number")});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error fetching reservations: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(btn, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setVisible(true);

        btn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int id_reserv = (int) model.getValueAt(selectedRow, 0);
                model.removeRow(selectedRow);
                reservation.cancelReservation(id_reserv);
                JOptionPane.showMessageDialog(this, "Reservation canceled successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to delete.", "No Row Selected", JOptionPane.WARNING_MESSAGE);
            }
        }
    }


}
