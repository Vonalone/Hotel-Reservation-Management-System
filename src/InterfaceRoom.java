import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InterfaceRoom extends JFrame implements ActionListener {

    private JTextField text1, text2, text3;
    private static final String[] columns = {"room number", "room type", "price"};
    private DefaultTableModel model = new DefaultTableModel(columns, 0);
    private JTable table = new JTable(model);
    private JPanel mainPanel = new JPanel(new BorderLayout());
    private JButton addButton = new JButton("+ Add");
    private JButton clearButton = new JButton("Clear");
    private JButton delButton = new JButton("Delete");
    private JButton uButton = new JButton("Update");

    public InterfaceRoom() {
        setTitle("Room Management");

        addButton.addActionListener(this);
        clearButton.addActionListener(this);
        delButton.addActionListener(this);
        uButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(delButton);
        buttonPanel.add(uButton);

        try {
            ResultSet resultSet = room.getAllRooms();
            while (resultSet.next()) {
                model.addRow(new Object[]{resultSet.getString("room_number"), resultSet.getString("type_room"), resultSet.getDouble("night_price")});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error fetching rooms: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        JPanel textPanel = new JPanel(new BorderLayout());
        text1 = new JTextField("room number:");
        text2 = new JTextField("room type:");
        text3 = new JTextField("price:");
        textPanel.add(text1, BorderLayout.NORTH);
        textPanel.add(text2, BorderLayout.CENTER);
        textPanel.add(text3, BorderLayout.SOUTH);

        mainPanel.add(textPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(table), BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().add(mainPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String room_number = text1.getText();
            String room_type = text2.getText();
            String price_night = text3.getText();

            if (room_number.isEmpty() || room_type.isEmpty() || price_night.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Empty fields detected!");
            } else {
                try {
                    int room_num = Integer.parseInt(room_number);
                    double price = Double.parseDouble(price_night);
                    room.addRoom(1, room_num, room_type, price);
                    model.addRow(new Object[]{room_number, room_type, price});
                    JOptionPane.showMessageDialog(this, "Room added successfully.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Price must be a valid number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (e.getSource() == delButton) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String room_number = model.getValueAt(selectedRow, 0).toString();
                model.removeRow(selectedRow);
                room.deleteRoom(room_number);
                JOptionPane.showMessageDialog(this, "Room deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to delete.", "No Row Selected", JOptionPane.WARNING_MESSAGE);
            }
        } else if (e.getSource()==clearButton) {
            text1.setText("room number:");
            text2.setText("room type:");
            text3.setText("price:");
        }
        else if (e.getSource() == uButton) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String room_number = text1.getText();
                String room_type = text2.getText();
                String price_night = text3.getText();

                if (room_number.isEmpty() || room_type.isEmpty() || price_night.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Empty fields detected!");
                } else {
                    try {
                        int room_num = Integer.parseInt(room_number);
                        double price = Double.parseDouble(price_night);

                        model.setValueAt(room_num, selectedRow, 0);
                        model.setValueAt(room_type, selectedRow, 1);
                        model.setValueAt(price, selectedRow, 2);


                        room.updateRoomPrice(room_num, price);
                        JOptionPane.showMessageDialog(this, "Room updated successfully.");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Price must be a valid number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to update.", "No Row Selected", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
