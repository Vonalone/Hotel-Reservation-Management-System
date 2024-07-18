import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class login extends JFrame implements ActionListener {
    private JPanel panel;
    private JLabel label1 = new JLabel("Wolcome Back");
    private JLabel label2 = new JLabel("Enter your CIN");
    private JTextField txt = new JTextField();
    private JLabel label3 = new JLabel("Enter your Password");
    private JPasswordField pf = new JPasswordField();
    private  JButton btn1 = new JButton("Log In");
    private JButton btn2 = new JButton("Registre");
    public login(String Titre){
        super(Titre);
        this.setSize(500,375);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        panel=(JPanel) this.getContentPane();
        panel.setLayout(null);
        label1.setBounds(200,29,200,30);
        label1.setBackground(Color.red);
        label1.setOpaque(false);
        label2.setBounds(24,90,125,26);
        txt.setBounds(200,90,200,30);
        label3.setBounds(24,150,125,26);
        pf.setBounds(200,150,200,30);
        btn1.setBounds(310,220,90,35);
        btn2.setBounds(120,220,90,35);
        panel.add(label1);
        panel.add(label2);
        panel.add(txt);
        panel.add(label3);
        panel.add(pf);
        panel.add(btn1);
        panel.add(btn2);
        btn1.addActionListener(this);
        btn2.addActionListener(this);

    }
    public static  void main(String[] args){
        login f =new login("login page");

        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btn2){
            register f1 =new register("register page");
            f1.setVisible(true);
            this.dispose();
        }
        else{

            String CinInput=txt.getText();
            String PasswordInput=pf.getText();
            user u = new user(CinInput, PasswordInput);
            if(CinInput.length()==0 || PasswordInput.length()==0){
                txt.setText("");
                pf.setText("");
                JOptionPane.showMessageDialog(this,"Empty Champs !");
            } else if (u.authenticate()) {
                JOptionPane.showMessageDialog(this,"succes login");
                if(u.is_admin()){
                    SwingUtilities.invokeLater(() -> new admin(CinInput));}
                else{
                    SwingUtilities.invokeLater(() -> new interfaceClient(u.FirstName().toUpperCase(),u.Email(),CinInput,u.getPhone_number()));}
                this.dispose();
            }
            else{
                txt.setText("");
                pf.setText("");
                JOptionPane.showMessageDialog(this,"cin or password not correct");
            }
        }
    }
}
