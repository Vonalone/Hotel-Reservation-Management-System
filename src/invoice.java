import java.sql.*;
import java.time.LocalDate;
import java.util.Date;

public class invoice {
    private int invoice_id;
    private String payment_date;
    private Double price;

    public invoice(int invoice_id, String payment_date, Double price) {
        this.invoice_id = invoice_id;
        this.payment_date = payment_date;
        this.price = price;
    }

    public static void addInvoice(int user_id, double price) {
        try {
            Connection con = ConnexionJV.openConnection();
            LocalDate myDate = LocalDate.now();
            String paymentDate = myDate.toString();
            System.out.println("ADD NEW invoice ...");
            String sql = "INSERT INTO invoice (invoice_user_id,payment_date,price) VALUES (?,?,?);";
            PreparedStatement PrStmt = con.prepareStatement(sql);
            PrStmt.setInt(1,user_id);
            PrStmt.setString(2,paymentDate);
            PrStmt.setDouble(3,price);
            PrStmt.executeUpdate();
            System.out.println("ivoice IS SAVE");
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static ResultSet DisplayInvoice() throws SQLException {
            Connection con = ConnexionJV.openConnection();
            String sql = "SELECT invoice_id,invoice_user_id,price,payment_date FROM invoice ";
            PreparedStatement PrStmt=con.prepareStatement(sql);
            return PrStmt.executeQuery();}
    public static ResultSet DisplayInvoiceUser(int userId) throws SQLException {
        Connection con = ConnexionJV.openConnection();
        String sql = "SELECT invoice_id,invoice_user_id,price,payment_date FROM invoice WHERE invoice_user_id = ?";
        PreparedStatement PrStmt =con.prepareStatement(sql);
        PrStmt.setInt(1,userId);
        return PrStmt.executeQuery();}
    public static void deleteInvoice(int invoice_id){
        try {
            Connection con = ConnexionJV.openConnection();
            String sql = "DELETE FROM invoice where invoice_id=?";
            PreparedStatement PrStmt = con.prepareStatement(sql);
            PrStmt.setInt(1,invoice_id);
            PrStmt.executeUpdate();
            con.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}