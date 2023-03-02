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
            String sql = "select * from checkin";
            ResultSet rs = stt.executeQuery(sql);
            while (rs.next()) {
                Integer id = rs.getInt("id");
                Integer customer_id = rs.getInt("customer_id");
                Integer room_id = rs.getInt("room_id");
                Date checkIn = rs.getDate("checkindate");
                Date checkOut = rs.getDate("checkoutDate");
                String note = rs.getString("note");
                CheckIn c = new CheckIn(id, customer_id, room_id, checkIn, checkOut, note);
                listCheckIn.add(c);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listCheckIn;
    }

    @Override
    public boolean create(CheckIn checkIn) {
        try {
            Database db = Database.getInstance();
            Statement stt = db.getStatement();
            String sql = "insert into checkin(customer_id, room_id, checkindate, checkoutDate, note) values('"+checkIn.getCustomer_id()+"','"+checkIn.getRoom_id()+"','"+checkIn.getCheckindate()+"','"+checkIn.getCheckoutDate()+"','"+checkIn.getNote()+"')";
            if (stt.executeUpdate(sql) > 0) {
                return true;
            }
        } catch (Exception e) {

        }
        return false;
    }

    @Override
    public boolean update(CheckIn checkIn) {
        try {
            Database db = Database.getInstance();
            Statement stt = db.getStatement();
            String sql = "update checkin set customer_id='" + checkIn.getCustomer_id() + "', room_id = '" + checkIn.getRoom_id()+ "', checkindate = '" + checkIn.getCheckindate()+ "', checkoutDate = '" + checkIn.getCheckoutDate()+ "', note = '" + checkIn.getNote() + "'where id= " + checkIn.getId();
            if (stt.executeUpdate(sql) > 0) {
                return true;
            }
        } catch (Exception e) {

        }
        return false;
    }

    @Override
    public boolean delete(CheckIn checkIn) {
        try {
            Database db = Database.getInstance();
            Statement stt = db.getStatement();
            String sql = "delete from checkin where id= " + checkIn.getId();
            if (stt.executeUpdate(sql) > 0) {
                return true;
            }
        } catch (Exception e) {

        }
        return false;
    }

    @Override
    public CheckIn find(Integer id) {
        try {
            Database db = Database.getInstance();
            Statement stt = db.getStatement();
            String sql = "select * from checkin where id ="+id;
            ResultSet rs = stt.executeQuery(sql);
            while (rs.next()) {
                Integer customer_id = rs.getInt("customer_id");
                Integer room_id = rs.getInt("room_id");
                Date checkIn = rs.getDate("checkindate");
                Date checkOut = rs.getDate("checkoutDate");
                String note = rs.getString("note");
                CheckIn c = new CheckIn(id, customer_id, room_id, checkIn, checkOut, note);
                return c;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean booking(CheckIn checkIn) {
        try {
            Database db = Database.getInstance();
            Statement stt = db.getStatement();
            String sql = "update room set status = 'booked' where id ="+checkIn.getRoom_id();
            if (stt.executeUpdate(sql) > 0) {
                return true;
            }
        } catch (Exception e) {

        }
        return false;
    }

    public boolean notBooking(CheckIn checkIn) {
        try {
            Database db = Database.getInstance();
            Statement stt = db.getStatement();
            String sql = "update room set status = 'not booked' where id ="+checkIn.getRoom_id();
            if (stt.executeUpdate(sql) > 0) {
                return true;
            }
        } catch (Exception e) {

        }
        return false;
    }

    public CheckIn findByCusID(Integer cid) {
        try {
            Database db = Database.getInstance();
            Statement stt = db.getStatement();
            String sql = "select * from checkin where customer_id ="+cid;
            ResultSet rs = stt.executeQuery(sql);
            while (rs.next()) {
                Integer id = rs.getInt("id");
                Integer room_id = rs.getInt("room_id");
                Date checkIn = rs.getDate("checkindate");
                Date checkOut = rs.getDate("checkoutDate");
                String note = rs.getString("note");
                CheckIn c = new CheckIn(id, cid, room_id, checkIn, checkOut, note);
                return c;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
