package daopatern;

import database.Database;
import entities.Bills;
import javafx.scene.control.Alert;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class BillsDao implements DAOInterface<Bills>{
    private static BillsDao instance;

    private BillsDao(){

    }

    public static BillsDao getInstance() {
        if(instance == null){
            instance = new BillsDao();
        }
        return instance;
    }

    public ArrayList<Bills> getAll() {
        ArrayList<Bills> listBills = new ArrayList<>();
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
                Bills b = new Bills(id, customer_id, price, checkoutdate, payment);
                listBills.add(b);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return listBills;
    }

    @Override
    public boolean create(Bills bills) {
        try {
            Database db = Database.getInstance();
            Statement stt = db.getStatement();
            String sql = "insert into bill(customer_id, price, checkoutDate, payments) values('"+bills.getCheckin_id()+"','"+bills.getPrice()+"','"+bills.getCheckoutDate()+"','"+bills.getPayments()+"')";
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
    public boolean update(Bills bills) {
        try {
            Database db = Database.getInstance();
            Statement stt = db.getStatement();
            String sql = "update bill set customer_id='" + bills.getCheckin_id()+ "', price = '" + bills.getPrice()+ "', checkoutDate = '" + bills.getCheckoutDate()+ "', payments = '" + bills.getPayments()+ "'where id= " + bills.getId();
            if (stt.executeUpdate(sql) > 0) {
                return true;
            }
        } catch (Exception e) {

        }
        return false;
    }

    @Override
    public boolean delete(Bills bills) {
        return false;
    }

    @Override
    public Bills find(Integer id) {
        return null;
    }
}
