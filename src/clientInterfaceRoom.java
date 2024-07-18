import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class clientInterfaceRoom extends JFrame {
    private static final long serialVersionUID = 1L;

    public clientInterfaceRoom() {
        setTitle("Hotel Management");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        JTextField type_room = new JTextField("   Enter type of room   ");
        JTextField Date_Enter = new JTextField("   Enter Date Enter  ");
        JTextField Date_Exist = new JTextField("   Enter Date Exist  ");
        JButton btn4 = new JButton("Search");
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;
        constraints.insets = new Insets(5, 5, 5, 5);
        panel.add(type_room, constraints);
        panel.add(Date_Enter, constraints);
        panel.add(Date_Exist, constraints);

        JPanel roomPanel = new JPanel();
        roomPanel.setLayout(new BoxLayout(roomPanel, BoxLayout.Y_AXIS));

        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String type_rm = type_room.getText();
                String Date_Etr = Date_Enter.getText();
                String Date_srt = Date_Exist.getText();
                try {
                    ResultSet resultSet = room.getAvailableRooms(Date_Etr, Date_srt);
                    roomPanel.removeAll();
                    while (resultSet.next()) {
                        String roomNumber = resultSet.getString("room_number");
                        String roomType = resultSet.getString("type_room");
                        double nightPrice = resultSet.getDouble("night_price");
                        JPanel roomItemPanel = createRoomPanel(roomNumber, roomType, nightPrice);
                        roomPanel.add(roomItemPanel);
                    }
                    roomPanel.revalidate();
                    roomPanel.repaint(); 
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(clientInterfaceRoom.this, "Error fetching rooms: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        constraints.gridy = 1;
        constraints.gridwidth = 3;
        panel.add(btn4, constraints);
        add(panel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(roomPanel);
        add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel createRoomPanel(String roomNumber, String roomType, double nightPrice) {
        JPanel roomPanel = new JPanel(new BorderLayout());

        JLabel roomNumberLabel = new JLabel("Room Number: " + roomNumber);
        JLabel roomTypeLabel = new JLabel("Room Type: " + roomType);
        JLabel nightPriceLabel = new JLabel("Night Price: $" + nightPrice);

        JButton reserveButton = new JButton("Reserve");
        reserveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(clientInterfaceRoom.this, "Room " + roomNumber + " reserved successfully!");
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(reserveButton);

        roomPanel.add(roomNumberLabel, BorderLayout.NORTH);
        roomPanel.add(roomTypeLabel, BorderLayout.CENTER);
        roomPanel.add(nightPriceLabel, BorderLayout.SOUTH);
        roomPanel.add(buttonPanel, BorderLayout.EAST);

        roomPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        return roomPanel;
    }
}
