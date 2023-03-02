package daopatern;

import database.Database;
import entities.*;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class RoomDao implements DAOInterface<RoomInfo>{
    private static RoomDao instance;

    private RoomDao() {

    }

    public static RoomDao getInstance() {
        if (instance == null) {
            instance = new RoomDao();
        }
        return instance;
    }
//    public ArrayList<Room> getAll() {
//        ArrayList<Room> listRoom = new ArrayList<>();
//        try {
//            Database db = Database.getInstance();
//            Statement stt = db.getStatement();
//            String sql = "SELECT * FROM room";
//            ResultSet rs = stt.executeQuery(sql);
//            while (rs.next()) {
//                Integer id = rs.getInt("id");
//                String name = rs.getString("name");
//                Integer roomtype_id = rs.getInt("roomtype_id");
//                String status = rs.getString("status");
//                Integer floor_id = rs.getInt("floor_id");
//                Room c = new Room(id, name, status, floor_id, roomtype_id);
//                listRoom.add(c);
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return listRoom;
//    }

    public ArrayList<RoomType> getAllRoomType() {
        ArrayList<RoomType> listRoomType = new ArrayList<>();
        try {
            Database db = Database.getInstance();
            Statement stt = db.getStatement();
            String sql = "SELECT * FROM roomtype";
            ResultSet rs = stt.executeQuery(sql);
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                Double price = rs.getDouble("price");
                Integer numberBeds = rs.getInt("numberBeds");
                Integer peopleMax = rs.getInt("peopleMax");
                RoomType c = new RoomType(id, name, price,numberBeds,peopleMax);
                listRoomType.add(c);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listRoomType;
    }
    public ArrayList<Floor> getAllFloor() {
        ArrayList<Floor> listFloor = new ArrayList<>();
        try {
            Database db = Database.getInstance();
            Statement stt = db.getStatement();
            String sql = "SELECT * FROM floor";
            ResultSet rs = stt.executeQuery(sql);
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                Floor c = new Floor(id, name);
                listFloor.add(c);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listFloor;
    }
    public ArrayList<RoomInfo> getAll() {
        ArrayList<RoomInfo> list = new ArrayList<>();
        try {
            Database db = Database.getInstance();
            Statement stt = db.getStatement();
            String sql = "SELECT a.id, a.name, b.name as roomtype,c.name as floor, a.status, b.price, a.roomtype_id, a.floor_id FROM room a INNER JOIN roomtype b on a.roomtype_id = b.id INNER JOIN floor c on a.floor_id = c.id ORDER BY a.id ASC";
            ResultSet rs = stt.executeQuery(sql);
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                String roomtype = rs.getString("roomtype");
                String floor = rs.getString("floor");
                String status = rs.getString("status");
                Double price = rs.getDouble("price");
                RoomInfo c = new RoomInfo(id, name, roomtype, floor, status, price);
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public boolean create(RoomInfo roomInfo) {
        return false;
    }

    @Override
    public boolean update(RoomInfo roomInfo) {
        return false;
    }

    @Override
    public boolean delete(RoomInfo roomInfo) {
        return false;
    }


    @Override
    public RoomInfo find(Integer id) {
        try {
            Database db = Database.getInstance();
            Statement stt = db.getStatement();
            String sql = "SELECT a.id, a.name, b.name as roomtype,c.name as floor, a.status, b.price, a.roomtype_id, a.floor_id FROM room a INNER JOIN roomtype b on a.roomtype_id = b.id INNER JOIN floor c on a.floor_id = c.id where a.id="+id;
            ResultSet rs = stt.executeQuery(sql);
            while (rs.next()) {
                String name = rs.getString("name");
                String roomtype = rs.getString("roomtype");
                String floor = rs.getString("floor");
                String status = rs.getString("status");
                Double price = rs.getDouble("price");
                RoomInfo c = new RoomInfo(id, name, roomtype, floor, status, price);
                return c;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public ArrayList<RoomInfo> getAllNotBooked() {
        ArrayList<RoomInfo> list = new ArrayList<>();
        try {
            Database db = Database.getInstance();
            Statement stt = db.getStatement();
            String sql = "SELECT a.id, a.name, b.name as roomtype,c.name as floor, a.status, b.price, a.roomtype_id, a.floor_id FROM room a INNER JOIN roomtype b on a.roomtype_id = b.id INNER JOIN floor c on a.floor_id = c.id where a.status like 'not booked' ORDER BY a.id ASC";
            ResultSet rs = stt.executeQuery(sql);
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                String roomtype = rs.getString("roomtype");
                String floor = rs.getString("floor");
                String status = rs.getString("status");
                Double price = rs.getDouble("price");
                RoomInfo c = new RoomInfo(id, name, roomtype, floor, status, price);
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public double getPriceByRoomTypeId(int roomTypeId) {
        try {
            Database db = Database.getInstance();
            Statement stt = db.getStatement();
            String sql = "SELECT price FROM roomtype WHERE id in (SELECT roomtype_id FROM room WHERE id = "+roomTypeId+");";
            ResultSet rs = stt.executeQuery(sql);
            while (rs.next()) {
                Double price = rs.getDouble("price");
                return price;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0.0;
    }
}
