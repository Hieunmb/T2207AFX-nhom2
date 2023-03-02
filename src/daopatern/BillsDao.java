package daopatern;

import database.Database;
import entities.Bills;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;

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
                Double price = rs.getDouble("price");
                String payment = rs.getString("payment");
                Date checkoutDate = rs.getDate("checkoutDate");
                Bills b = new Bills(id, customer_id, price, payment, checkoutDate);
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
            String sql = "insert into bill(customer_id, price, checkoutDate, payments) values('"+bills.getCustomer_id()+"','"+bills.getPrice()+"','"+bills.getCheckoutdate()+"','"+bills.getPayment()+"')";
            if (stt.executeUpdate(sql) > 0){
                return true;
            }
        }catch (Exception e){

        }
        return false;
    }

    @Override
    public boolean update(Bills bills) {
        try{
            Database db = Database.getInstance();
            Statement stt = db.getStatement();
            String sql = "update bill set customer_id='" +bills.getCustomer_id()+"',price = '" +bills.getPrice()+"',checkoutDate = '" +bills.getCheckoutdate()+"',payments = '" +bills.getPayment()+ "'where id = "+bills.getId();
            if(stt.executeUpdate(sql) > 0){
                return true;
            }
        }catch (Exception e){

        }
        return false;
    }

    @Override
    public boolean delete(Bills bills) {
        try{
            Database db = Database.getInstance();
            Statement stt = db.getStatement();
            String sql = "delete from bill where id= " +bills.getId();
            if(stt.executeUpdate(sql) >0){
                return true;
            }
        }catch (Exception e){

        }
        return false;
    }

    @Override
    public Bills find(Integer id) {
        try{
            Database db = Database.getInstance();
            Statement stt = db.getStatement();
            String sql = "select * from bill where id="+id;
            ResultSet rs = stt.executeQuery(sql);
            while (rs.next()) {
                Integer customer_id = rs.getInt("customer_id");
                Double price = rs.getDouble("price");
                String payment = rs.getString("payment");
                Date checkoutDate = rs.getDate("checkoutDate");
                Bills b = new Bills(id, customer_id, price, payment, checkoutDate);
                return b;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
