package daopatern;

import database.Database;
import entities.CheckIn;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CheckInDao {
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
            String sql = "select * from checkin";
            ResultSet rs = stt.executeQuery(sql);
            while (rs.next()) {
                Integer id = rs.getInt("id");
                Integer cus = rs.getInt("customer_id");
                Integer room = rs.getInt("room_id");
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
}
