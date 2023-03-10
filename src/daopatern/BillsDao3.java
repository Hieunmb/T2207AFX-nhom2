package daopatern;

import database.Database;
import entities.Bills3;
import entities.CheckIn;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class BillsDao3 implements DAOInterface<Bills3>{

    private static BillsDao3 instance;

    private BillsDao3() {

    }

    public static BillsDao3 getInstance() {
        if (instance == null) {
            instance = new BillsDao3();
        }
        return instance;
    }
    @Override
    public ArrayList<Bills3> getAll() {
        ArrayList<Bills3> listBills = new ArrayList<>();
        try {
            Database db = Database.getInstance();
            Statement stt = db.getStatement();
            String sql = "SELECT a.id, b.name as customer, a.price, a.checkinDate, a.checkoutDate, a.payments, a.status FROM bill a INNER JOIN customer b on a.customer_id = b.id";
            ResultSet rs = stt.executeQuery(sql);
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String name = rs.getString("customer");
                Float price = rs.getFloat("price");
                String checkindate = rs.getString("checkinDate");
                String payment = rs.getString("payments");
                String checkoutdate = rs.getString("checkoutDate");
                String status = rs.getString("status");
                Bills3 b = new Bills3(id, name, price, checkindate, checkoutdate, payment, status);
                listBills.add(b);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listBills;
    }

    @Override
    public boolean create(Bills3 classes) {
        return false;
    }

    @Override
    public boolean update(Bills3 classes) {
        return false;
    }

    @Override
    public boolean delete(Bills3 classes) {
        return false;
    }

    @Override
    public Bills3 find(Integer id) {
        return null;
    }

    public boolean payments(Bills3 bills3) {
        try {
            Database db = Database.getInstance();
            Statement stt = db.getStatement();
            String sql = "update bill set status = 'paid' where id ="+bills3.getId();
            if (stt.executeUpdate(sql) > 0) {
                return true;
            }
        } catch (Exception e) {

        }
        return false;
    }
}
