package daopatern;

import database.Database;
import entities.Customer;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CusDao implements DAOInterface<Customer>{
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
    public boolean create(Customer customer) {
        try {
            Database db = Database.getInstance();
            Statement stt = db.getStatement();
            String sql = "insert into customer(name, cccd, nationality, phone, gender) values('"+customer.getName()+"','"+customer.getCccd()+"','"+customer.getNationality()+"','"+customer.getPhone()+"','"+customer.getGender()+"')";
            if (stt.executeUpdate(sql) > 0) {
                return true;
            }
        } catch (Exception e) {

        }
        return false;
    }
    public boolean update(Customer customer) {
        try {
            Database db = Database.getInstance();
            Statement stt = db.getStatement();
            String sql = "update customer set name='" + customer.getName() + "', cccd = '" + customer.getCccd()+ "', nationality = '" + customer.getNationality()+ "', phone = '" + customer.getPhone()+ "', gender = '" + customer.getGender() + "'where id= " + customer.getId();
            if (stt.executeUpdate(sql) > 0) {
                return true;
            }
        } catch (Exception e) {

        }
        return false;
    }

    @Override
    public boolean delete(Customer customer) {
        try {
            Database db = Database.getInstance();
            Statement stt = db.getStatement();
            String sql = "delete from customer where id= " + customer.getId();
            if (stt.executeUpdate(sql) > 0) {
                return true;
            }
        } catch (Exception e) {

        }
        return false;
    }

    @Override
    public Customer find(Integer id) {
        try {
            Database db = Database.getInstance();
            Statement stt = db.getStatement();
            String sql = "select * from customer where id="+id;
            ResultSet rs = stt.executeQuery(sql);
            while (rs.next()) {
                String name = rs.getString("name");
                String cccd = rs.getString("cccd");
                String nationality = rs.getString("nationality");
                String phone = rs.getString("phone");
                String gender = rs.getString("gender");
                Customer c = new Customer(id, name, cccd, nationality, phone, gender);
                return c;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
