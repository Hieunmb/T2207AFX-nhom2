package daopatern;

import database.Database;
import entities.CheckIn;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CheckInDao implements DAOInterface<CheckIn>{
    private static CheckInDao instance;

    private CheckInDao() {

    }

    public static CheckInDao getInstance() {
        if (instance == null) {
            instance = new CheckInDao();
        }
        return instance;
    }

    public ArrayList<CheckIn> getAll() {
        ArrayList<CheckIn> listCheckIn = new ArrayList<>();
        try {
            Database db = Database.getInstance();
            Statement stt = db.getStatement();
            String sql = "select a.id, b.name as nameCus, c.name as nameRoom, a.checkindate, a.checkoutDate, a.note , b.id as idCus, c.id as idRoom, d.name as nameRoomType, d.price  FROM checkin a INNER JOIN customer b on a.customer_id = b.id INNER JOIN room c on a.room_id = c.id INNER JOIN roomtype d on c.roomtype_id = d.id";
            ResultSet rs = stt.executeQuery(sql);
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String cus = rs.getString("nameCus");
                String room = rs.getString("nameRoom");
                Date checkIn = rs.getDate("checkindate");
                Date checkOut = rs.getDate("checkoutDate");
                String note = rs.getString("note");
                CheckIn c = new CheckIn(id, cus, room, checkIn, checkOut, note);
                listCheckIn.add(c);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listCheckIn;
    }

    @Override
    public boolean create(CheckIn checkIn) {
        return false;
    }

    @Override
    public boolean update(CheckIn checkIn) {
        return false;
    }

    @Override
    public boolean delete(CheckIn checkIn) {
        return false;
    }

    @Override
    public CheckIn find(Integer id) {
        return null;
    }
}
