package daopatern;

import database.Database;
import entities.Customer;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CusDao {
    private static CusDao instance;

    private CusDao() {

    }

    public static CusDao getInstance() {
        if (instance == null) {
            instance = new CusDao();
        }
        return instance;
    }
    public ArrayList<Customer> getAll() {
        ArrayList<Customer> listCus = new ArrayList<>();
        try {
            Database db = Database.getInstance();
            Statement stt = db.getStatement();
            String sql = "select * from customer";
            ResultSet rs = stt.executeQuery(sql);
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                String cccd = rs.getString("cccd");
                String nationality = rs.getString("nationality");
                String phone = rs.getString("phone");
                String gender = rs.getString("gender");
                Customer c = new Customer(id, name, cccd, nationality, phone, gender);
                listCus.add(c);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listCus;
    }
}
