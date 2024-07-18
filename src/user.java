import java.io.FileWriter;
import java.sql.*;
import java.util.Scanner;

public class user {
    private String first_name;
    private String last_name;
    private String email;
    private String  password;
    private String cin ;
    private String phone_number;

    public user(){};

    public user(String first_name, String last_name, String email, String password, String cin, String phone_number) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.cin = cin;
        this.phone_number = phone_number;
    }

    public user(String cin,String password) {
        this.cin = cin;
        this.password = password;
    }
    public user(String cin) {
        this.cin = cin;
    }

    public boolean authenticate(){
        boolean login = false;
        try {
            Connection con = ConnexionJV.openConnection();
            String sql = "SELECT IF(cin = ? AND password = ?,true,false) as new from user";
            PreparedStatement PrStmt = con.prepareStatement(sql);
            PrStmt.setString(1,this.cin);
            PrStmt.setString(2,this.password);
            ResultSet rs = PrStmt.executeQuery();
            while (rs.next()) {
                if (rs.getInt("new") == 1) {
                    login = true;
                }
            }
            con.close();
            return login;
        }catch (Exception e){
            System.out.println(e);
        }
        return login;
    }
    public boolean checkCin(String userCin){
        boolean exist = false;
        try {
            Connection con = ConnexionJV.openConnection();
            String sql = "select IF(cin = ?,true,false) as new from user";
            PreparedStatement PrStmt=con.prepareStatement(sql);
            PrStmt.setString(1,userCin);
            ResultSet rs = PrStmt.executeQuery();
            while (rs.next()) {
                if (rs.getInt("new") == 1) {
                    exist = true;
                }
            }
            con.close();
            return exist;
        }catch (Exception e){
            System.out.println(e);
        }
        return exist;
    }
    public void registre(){
        try {
            Connection con = ConnexionJV.openConnection();
            String sql = "INSERT INTO user (first_name, last_name, email, password, cin, phone_number) VALUES (?, ?, ?, ?, ?, ?) ;";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, this.first_name);
            preparedStatement.setString(2, this.last_name);
            preparedStatement.setString(3, this.email);
            preparedStatement.setString(4, this.password);
            preparedStatement.setString(5, this.cin);
            preparedStatement.setString(6, this.phone_number);
            preparedStatement.executeUpdate();
            //System.out.println("Welcome to our service!");
            con.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    // read user par admin
    public static ResultSet displayAllUser() throws SQLException {
            Connection con = ConnexionJV.openConnection();
            String sql = "SELECT user_id,first_name,last_name,email,cin,phone_number FROM user";
            PreparedStatement Prstmt =con.prepareStatement(sql);
            return Prstmt.executeQuery();

    }


    //update user
    public void changePassword(String newPassword){
        try {
            Connection con = ConnexionJV.openConnection();
            String sql = "UPDATE user SET password =? where cin=?;";
            PreparedStatement PrStmt = con.prepareStatement(sql);
            PrStmt.setString(1,newPassword);
            PrStmt.setString(2,this.cin);
            PrStmt.executeUpdate();
            con.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    // delete room
    public static void deleteUser(String userCin){
        try {
            Connection con = ConnexionJV.openConnection();
            String sql = "DELETE FROM user where cin=?;";
            PreparedStatement PrStmt = con.prepareStatement(sql);
            PrStmt.setString(1,userCin);
            PrStmt.executeUpdate();
            con.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public boolean is_admin(){
        boolean admin = false;
        try {
            Connection con = ConnexionJV.openConnection();
            String sql = "SELECT is_admin FROM user where cin=?;";
            PreparedStatement PrStmt = con.prepareStatement(sql);
            PrStmt.setString(1,this.cin);
            ResultSet rs=PrStmt.executeQuery();
            while (rs.next()) {
                if (rs.getInt("is_admin") == 1) {
                    admin = true;
                }
            }

            con.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return admin;
    }
    public int userId(){
        int id;
        try {
            Connection con = ConnexionJV.openConnection();
            String sql = "select user_id FROM user where cin=?";
            PreparedStatement PrStmt = con.prepareStatement(sql);
            PrStmt.setString(1,this.cin);
            ResultSet rs =PrStmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("user_id");
                return id;
            } else {
                System.out.println("No user found for cin: " + this.cin);
            }
            con.close();

        }catch (Exception e){
            System.out.println(e);
        }
        return -1;
    }
    public String FirstName(){
        String first_name;
        try {
            Connection con = ConnexionJV.openConnection();
            String sql = "select first_name FROM user where cin=?";
            PreparedStatement PrStmt = con.prepareStatement(sql);
            PrStmt.setString(1,this.cin);
            ResultSet rs =PrStmt.executeQuery();
            if (rs.next()) {
                first_name = rs.getString("first_name");
                return first_name;
            }
            con.close();

        }catch (Exception e){
            System.out.println(e);
        }
        return "";
    }
    public String Email(){
        String email;
        try {
            Connection con = ConnexionJV.openConnection();
            String sql = "select email FROM user where cin=?";
            PreparedStatement PrStmt = con.prepareStatement(sql);
            PrStmt.setString(1,this.cin);
            ResultSet rs =PrStmt.executeQuery();
            if (rs.next()) {
                email = rs.getString("email");
                return email;
            }
            con.close();

        }catch (Exception e){
            System.out.println(e);
        }
        return "";
    }
    public String getPhone_number(){
        String Phone_number;
        try {
            Connection con = ConnexionJV.openConnection();
            String sql = "select phone_number FROM user where cin=?";
            PreparedStatement PrStmt = con.prepareStatement(sql);
            PrStmt.setString(1,this.cin);
            ResultSet rs =PrStmt.executeQuery();
            if (rs.next()) {
                Phone_number = rs.getString("phone_number");
                return Phone_number;
            }
            con.close();

        }catch (Exception e){
            System.out.println(e);
        }
        return "";
    }
    }





