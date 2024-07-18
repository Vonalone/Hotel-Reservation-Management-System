import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class admin extends JFrame {

    public admin(String cin) {
        JFrame frame = new JFrame("Admin Panel");

        JLabel label = new JLabel("WELCOME ADMIN", JLabel.CENTER);
        JPanel panel = new JPanel();
        JPanel jpanel = new JPanel();

        JMenuBar menu = new JMenuBar();
        JMenu room_management = new JMenu("Room Management");
        JMenu reservation_management = new JMenu("Reservation Management");
        JMenu client_management = new JMenu("Client Management");
        JMenu invoice_management = new JMenu("Invoice Management");

        JMenuItem room_management_item = new JMenuItem("Room Management");
        room_management_item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                InterfaceRoom f1 = new InterfaceRoom();
                f1.setLocationRelativeTo(null);
                f1.setVisible(true);
            }
        });

        JMenuItem client_management_item = new JMenuItem("Client Management");
        client_management_item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                InterfaceUser f2 = new InterfaceUser();
                f2.setLocationRelativeTo(null);
                f2.setVisible(true);
            }
        });

        JMenuItem reservation_management_item = new JMenuItem("Reservation Management");
        reservation_management_item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                InterfaceReserv f3 = new InterfaceReserv(cin);
                f3.setLocationRelativeTo(null);
                f3.setVisible(true);
            }
        });

        JMenuItem invoice_management_item = new JMenuItem("Invoice Management");
        invoice_management_item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                InterfaceInvoice f4 = new InterfaceInvoice(cin);
                f4.setLocationRelativeTo(null);
                f4.setVisible(true);
            }
        });

        room_management.add(room_management_item);
        client_management.add(client_management_item);
        reservation_management.add(reservation_management_item);
        invoice_management.add(invoice_management_item);

        menu.add(room_management);
        menu.add(reservation_management);
        menu.add(client_management);
        menu.add(invoice_management);

        ImageIcon icon = new ImageIcon("src/hotel.png");
        panel.add(new JLabel(icon));
        jpanel.setBackground(Color.decode("#007ffe"));
        jpanel.setOpaque(true);
        label.setForeground(Color.WHITE);
        jpanel.add(label);
        frame.setJMenuBar(menu);
        frame.add(panel, BorderLayout.CENTER);
        frame.add(jpanel, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


}
