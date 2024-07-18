import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class interfaceClient extends JFrame {

    public interfaceClient(String name_client,String email,String cin,String phone_number) {
        setTitle("Client Panel");

        JLabel label = new JLabel("WELCOME " + name_client, JLabel.CENTER);
        JPanel panel = new JPanel();
        JPanel jpanel = new JPanel();

        JMenuBar menu = new JMenuBar();
        JMenu roomsHotel = new JMenu("Rooms Hotel");
        JMenu yourReservations = new JMenu("Your Reservations");
        JMenu yourInvoice = new JMenu("Your Invoice");
        JMenu yourAccount = new JMenu("Your Account");

        JMenuItem roomsHotelItem = new JMenuItem("Rooms Hotel");
        roomsHotelItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clientInterfaceRoom f1 = new clientInterfaceRoom();
                f1.setLocationRelativeTo(null);
                f1.setVisible(true);
            }
        });

        JMenuItem yourReservationsItem = new JMenuItem("Your Reservations");
        yourReservationsItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                InterfaceReserv f2 = new InterfaceReserv(cin);
                f2.setLocationRelativeTo(null);
                f2.setVisible(true);
            }
        });

        JMenuItem yourInvoiceItem = new JMenuItem("Your Invoice");
        yourInvoiceItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                InterfaceInvoice f3 = new InterfaceInvoice(cin);
                f3.setLocationRelativeTo(null);
                f3.setVisible(true);
            }
        });

        JMenuItem yourAccountItem = new JMenuItem("Your Account");
        yourAccountItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    new interfaceAccount(name_client, email, cin, phone_number);
                });
            }
        });

        roomsHotel.add(roomsHotelItem);
        yourReservations.add(yourReservationsItem);
        yourInvoice.add(yourInvoiceItem);
        yourAccount.add(yourAccountItem);

        menu.add(roomsHotel);
        menu.add(yourReservations);
        menu.add(yourInvoice);
        menu.add(yourAccount);



        ImageIcon icon = new ImageIcon("src/hotel1.png");
        panel.add(new JLabel(icon));
        jpanel.setBackground(Color.decode("#007ffe"));
        jpanel.setOpaque(true);
        label.setForeground(Color.WHITE);
        jpanel.add(label);
        setJMenuBar(menu);
        add(panel, BorderLayout.CENTER);
        add(jpanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }




}

