import java.io.FileWriter;
import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class reservation {

        private int reserve_id;
        private int user_reserv_id;
        private int room_reserv_id;
        private String check_in_date;
        private String check_out_date;
        private int person_number;


    public reservation(int reserve_id, int user_reserv_id, int room_reserv_id, String check_in_date, String check_out_date, int person_number) {
        this.reserve_id = reserve_id;
        this.user_reserv_id = user_reserv_id;
        this.room_reserv_id = room_reserv_id;
        this.check_in_date = check_in_date;
        this.check_out_date = check_out_date;
        this.person_number = person_number;
    }

    //create
        public static void AddReservation(int user_reserv_id){
            try{
                Connection con = ConnexionJV.openConnection();
                try{
                    // Transaction because need create reservation with invoice
                con.setAutoCommit(false);
                System.out.println("Creating your reservation...");
                String sql ="INSERT INTO reservation (user_reserv_id,room_reserv_id,check_in_date,check_out_date, person_number) VALUES(?,?,?,?,?);";
                PreparedStatement PrStmt = con.prepareStatement(sql);
                Scanner s = new Scanner(System.in);
                System.out.println("enter number room  :");
                int number_room = s.nextInt();
                System.out.println("enter the date in:");
                String checkInDate = s.next();
                System.out.println("enter the date out :");
                String checkOutDate = s.next();
                System.out.println("enter the person number  :");
                int personNumber = s.nextInt();
                PrStmt.setInt(1,user_reserv_id);
                PrStmt.setInt(2,number_room);
                PrStmt.setString(3,checkInDate);
                PrStmt.setString(4,checkOutDate);
                PrStmt.setInt(5,personNumber);
                PrStmt.executeUpdate();
                System.out.println("Your reservation is complete");
                invoice.addInvoice(user_reserv_id,priceTotal(number_room,checkInDate,checkOutDate));
                con.commit();
                con.close();}catch (SQLException se){
                    System.out.println("Exist error please repeat reservation");
                    con.rollback();
                }

            }
            catch(Exception e){
                System.out.println(e);
            }


        }

        // display all reservations user
        public static ResultSet displayAllReservations() throws SQLException {

                Connection con = ConnexionJV.openConnection();
                    String sql = "SELECT reserv_id,user_reserv_id,room_reserv_id,check_in_date,check_out_date,person_number FROM reservation ";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            return preparedStatement.executeQuery();

        }
        // affiche reservation for user
        public static ResultSet displayUserReservations( int userId) throws SQLException {

                Connection con = ConnexionJV.openConnection();
                     String sql = "SELECT reserv_id, user_reserv_id, room_reserv_id, check_in_date, check_out_date, person_number FROM reservation WHERE user_reserv_id = ?" ;
                     PreparedStatement PrStmt =con.prepareStatement(sql);
                     PrStmt.setInt(1,userId);
                     return PrStmt.executeQuery();
        }

    public static void cancelReservation(int ReservationNumber){
        try {
            Connection con = ConnexionJV.openConnection();
            String sql = "DELETE FROM reservation where reserv_id=?";
            PreparedStatement PrStmt = con.prepareStatement(sql);
            PrStmt.setInt(1,ReservationNumber);
           // PrStmt.setInt(2,userId);
            PrStmt.executeUpdate();
            con.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }

        public static int NumberDays(String inDate,String outDate){
            LocalDate localDateIn = LocalDate.parse(inDate);
            LocalDate localDateOut = LocalDate.parse(outDate);
            Period period = Period.between(localDateIn,localDateOut);
            return Math.abs(period.getDays());
        }
        public static double priceTotal(int NbRoom,String inDate,String outDate){
        return NumberDays(inDate,outDate)*room.price_room(NbRoom);
        }
    }




