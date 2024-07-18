import java.io.FileInputStream;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class room {
    private int roomNumber;
    private String typeRoom;
    private double nightPrice;




    public room(int roomNumber, String typeRoom, double nightPrice) {
        this.roomNumber = roomNumber;
        this.typeRoom = typeRoom;
        this.nightPrice = nightPrice;
    }
    public room(){}


    // add room in console
    public static void addRoom(int id_admin, int roomNb, String typeRoom, double nightPrice) {
        try (Connection con = ConnexionJV.openConnection()) {
            String sql = "INSERT INTO ROOM (room_number, type_room, night_price, id_admin) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, roomNb);
            preparedStatement.setString(2, typeRoom);
            preparedStatement.setDouble(3, nightPrice);
            preparedStatement.setInt(4, id_admin);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }
    // add room exist in file
    public static void addRoomFromFile() {
        try {
            Connection con = ConnexionJV.openConnection();
            System.out.println("Adding New Rooms...");

            String sql = "INSERT INTO ROOM (room_number, type_room, night_price, id_admin) VALUES (?, ?, ?, ?);";
            PreparedStatement statement = con.prepareStatement(sql);

            FileInputStream file = new FileInputStream("roomInsert.txt");
            Scanner scanner = new Scanner(file);
            int roomCount = 0;

            System.out.println("Room list taken from file 'room.txt':");
            System.out.println("room_number\t|\ttype_room\t|\tnight_price\t|\tid_admin");

            while (scanner.hasNextLine()) {
                System.out.println("--- Adding room " + ++roomCount + " ---");
                String roomNb = scanner.next();
                String typeRoom = scanner.next();
                float nightPrice = scanner.nextFloat();
                int idAdmin = scanner.nextInt();

                statement.setString(1, roomNb);
                statement.setString(2, typeRoom);
                statement.setFloat(3, nightPrice);
                statement.setInt(4, idAdmin);
                statement.executeUpdate();

                System.out.println("Room saved successfully.");
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // display room


    public static ResultSet getAllRooms() throws SQLException {
                Connection con = ConnexionJV.openConnection();
                String sql = "SELECT room_number, type_room, night_price FROM ROOM";
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                return preparedStatement.executeQuery();
            }


    //update room
    public static void updateRoomPrice(int roomNb,double newPrice) {
        try {
            Connection con = ConnexionJV.openConnection();
            Scanner s =new Scanner(System.in);
            String sql = "UPDATE room SET night_price =? where room_number= ? ";
            PreparedStatement PrStmt = con.prepareStatement(sql);

            PrStmt.setDouble(1,newPrice);
            PrStmt.setInt(2,roomNb);
            PrStmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // delete room
    public static void deleteRoom(String roomNb) {
        try {
            Connection con = ConnexionJV.openConnection();
            Scanner s = new Scanner(System.in);
            String sql = "DELETE FROM room where room_number=?";
            PreparedStatement PrStmt = con.prepareStatement(sql);
            PrStmt.setString(1,roomNb);
            PrStmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public static float price_room(int roomNb){
        float price;
        try {
            Connection con = ConnexionJV.openConnection();
            Scanner s =new Scanner(System.in);
            String sql = "select night_price FROM room where room_number= ?";
            PreparedStatement PrStmt = con.prepareStatement(sql);
            PrStmt.setInt(1,roomNb);
            ResultSet rs =PrStmt.executeQuery();
            if (rs.next()) {
                price = rs.getFloat("night_price");
                return price;
            }

            con.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return -1;
    }

        public static ResultSet getAvailableRooms(String checkInDate, String checkOutDate) throws SQLException {
            List<Integer> availableRoomNumbers = new ArrayList<>();

            String getAllRoomsQuery = "SELECT room_number FROM room";
            String getBookedRoomsQuery = "SELECT DISTINCT room_reserv_id FROM reservation WHERE check_in_date <= ? AND check_out_date >= ?";

            try (Connection connection = ConnexionJV.openConnection();
                 PreparedStatement ps = connection.prepareStatement(getBookedRoomsQuery)) {
                ps.setDate(1, Date.valueOf(checkOutDate));
                ps.setDate(2, Date.valueOf(checkInDate));
                ResultSet bookedRoomsResult = ps.executeQuery();

                Set<Integer> bookedRooms = new HashSet<>();
                while (bookedRoomsResult.next()) {
                    bookedRooms.add(bookedRoomsResult.getInt("room_reserv_id"));
                }

                try (Statement statement = connection.createStatement();
                     ResultSet allRoomsResult = statement.executeQuery(getAllRoomsQuery)) {
                    while (allRoomsResult.next()) {
                        int roomNumber = allRoomsResult.getInt("room_number");
                        if (!bookedRooms.contains(roomNumber)) {
                            availableRoomNumbers.add(roomNumber);
                        }
                    }
                }
            }

            Connection connection = ConnexionJV.openConnection();
            String sql = "SELECT room_number, type_room, night_price FROM room WHERE room_number IN (";
            for (int i = 0; i < availableRoomNumbers.size(); i++) {
                if (i > 0) {
                    sql += ",";
                }
                sql += "?";
            }
            sql += ")";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < availableRoomNumbers.size(); i++) {
                preparedStatement.setInt(i + 1, availableRoomNumbers.get(i));
            }
            return preparedStatement.executeQuery();
        }
        public static void main(String args[]){
            try {
                ResultSet resultSet = room.getAvailableRooms("2024-02-01","2024-02-03");
                while (resultSet.next()) {
                    System.out.println(resultSet.getString("room_number"));
                    System.out.println(resultSet.getString("type_room"));
                    System.out.println(resultSet.getDouble("night_price"));
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }



