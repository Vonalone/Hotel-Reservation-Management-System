import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class register  extends JFrame implements ActionListener {
    private JPanel panel;
    private JLabel lbl1 = new JLabel("Create Account");
    private JLabel lbl2 = new JLabel("First Name");
    private JTextField txt1 =new JTextField();
    private JLabel lbl3 = new JLabel("Last Name");
    private JTextField txt2 =new JTextField();
    private JLabel lbl4 = new JLabel("Email");
    private JTextField txt3 =new JTextField();
    private JLabel lbl5 = new JLabel("CIN");
    private JTextField txt4 =new JTextField();
    private JLabel lbl6 = new JLabel("Password");
    private JPasswordField pf=new JPasswordField();
    private JLabel lbl7 = new JLabel("Number Phone");
    private JTextField txt5 =new JTextField();
    private  JButton btn2 = new JButton("Log In");
    private JButton btn1 =new JButton("Registre");
    public register(String Titre){
        super(Titre);
        this.setSize(500,575);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        panel=(JPanel) this.getContentPane();
        panel.setLayout(null);
        lbl1.setBounds(200,29,200,30);

        lbl2.setBounds(24,90,125,26);
        txt1.setBounds(200,90,200,30);
        lbl3.setBounds(24,150,125,26);
        txt2.setBounds(200,150,200,30);

        lbl4.setBounds(24,210,125,26);
        txt3.setBounds(200,210,200,30);

        lbl5.setBounds(24,270,125,26);
        txt4.setBounds(200,270,200,30);

        lbl7.setBounds(24,330,125,26);
        txt5.setBounds(200,330,200,30);

        lbl6.setBounds(24,390,125,26);
        pf.setBounds(200,390,200,30);

        btn1.setBounds(310,450,90,35);
        btn2.setBounds(120,450,90,35);
        panel.add(lbl1);
        panel.add(lbl2);
        panel.add(txt1);
        panel.add(lbl3);
        panel.add(txt2);
        panel.add(lbl4);
        panel.add(txt3);
        panel.add(lbl5);
        panel.add(txt4);
        panel.add(lbl6);
        panel.add(pf);
        panel.add(lbl7);
        panel.add(txt5);
        panel.add(btn1);
        panel.add(btn2);
        btn1.addActionListener(this);
        btn2.addActionListener(this);

    }
    public static  void main(String[] args){
        register f =new register("login page");

        f.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btn2){
            login f1 =new login("login page");
            f1.setVisible(true);
            this.dispose();
        }else {
            String FirstName = txt1.getText();
            String LastName = txt2.getText();
            String Email = txt3.getText();
            String CinInput = txt4.getText();
            String PasswordInput = pf.getText();
            String NumberPhone = txt5.getText();
            user u = new user(FirstName, LastName, Email, PasswordInput, CinInput, NumberPhone);
            if (FirstName.length() == 0 ||LastName.length() == 0 ||Email.length() == 0 ||CinInput.length() == 0 || PasswordInput.length() == 0|| NumberPhone.length() == 0 ) {
                JOptionPane.showMessageDialog(this, "Empty Champs !");
            } else if (!u.authenticate()) {
                if(!u.checkCin(CinInput)){
                    u.registre();
                    JOptionPane.showMessageDialog(this, "register succes");
                }else{
                    JOptionPane.showMessageDialog(this, "this CIN is already using");
                    txt4.setText("");
                }
            } else {

                JOptionPane.showMessageDialog(this, "already have account");
            }
        }
    }
}

