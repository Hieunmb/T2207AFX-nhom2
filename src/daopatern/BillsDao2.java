package daopatern;

import database.Database;
import entities.Bills;
import entities.Bills2;
import javafx.scene.control.Alert;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class BillsDao2 implements DAOInterface<Bills2> {
    private static BillsDao2 instance;

    private BillsDao2() {

    }

    public static BillsDao2 getInstance() {
        if (instance == null) {
            instance = new BillsDao2();
        }
        return instance;
    }

    @Override
    public ArrayList<Bills2> getAll() {
        ArrayList<Bills2> listBills = new ArrayList<>();
        try {
            Database db = Database.getInstance();
            Statement stt = db.getStatement();
            String sql = "select * from bill";
            ResultSet rs = stt.executeQuery(sql);
            while (rs.next()) {
                Integer id = rs.getInt("id");
                Integer customer_id = rs.getInt("customer_id");
                Integer price = rs.getInt("price");
                String payment = rs.getString("payments");
                Date checkoutdate = rs.getDate("checkoutDate");
                Bills2 b = new Bills2(id, customer_id, price, checkoutdate, payment);
                listBills.add(b);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listBills;
    }

    @Override
    public boolean create(Bills2 bills2) {
        try {
            Database db = Database.getInstance();
            Statement stt = db.getStatement();
            String sql = "insert into bill(customer_id, price, checkoutDate, payments) values('" + bills2.getCustomer_id() + "','" + bills2.getPrice() + "','" + bills2.getCheckoutDate() + "','" + bills2.getPayments() + "')";
            if (stt.executeUpdate(sql) > 0) {
                return true;
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(e.getMessage());
            alert.show();
        }
        return false;
    }

    @Override
    public boolean update(Bills2 classes) {
        return false;
    }

    @Override
    public boolean delete(Bills2 classes) {
        return false;
    }

    @Override
    public Bills2 find(Integer id) {
        return null;
    }
}
