package daopatern;

import database.Database;
import entities.Room;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class RoomDao {
    private static RoomDao instance;

    private RoomDao() {

    }

    public static RoomDao getInstance() {
        if (instance == null) {
            instance = new RoomDao();
        }
        return instance;
    }
    public ArrayList<Room> getAll() {
        ArrayList<Room> listRoom = new ArrayList<>();
        try {
            Database db = Database.getInstance();
            Statement stt = db.getStatement();
            String sql = "select * from room";
            ResultSet rs = stt.executeQuery(sql);
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                String status = rs.getString("status");
                Integer floor_id = rs.getInt("floor_id");
                Integer roomtype_id = rs.getInt("roomtype_id");
                Room c = new Room(id, name, status, floor_id, roomtype_id);
                listRoom.add(c);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listRoom;
    }
}
