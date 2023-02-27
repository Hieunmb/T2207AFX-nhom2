package daopatern;

import database.Database;
import entities.Bills;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class BillsDao {
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
                Integer cus_id = rs.getInt("cus_id");
                Integer price = rs.getInt("price");
                String payment = rs.getString("payment");
                Date checkoutdate = rs.getDate("checkoutdate");
                Bills b = new Bills(id, cus_id, price, payment, checkoutdate);
                listBills.add(b);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return listBills;
    }
}
