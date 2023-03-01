package daopatern;

import database.Database;
import entities.Service;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ServiceDao implements DAOInterface<Service> {
    private static ServiceDao instance;
    private ServiceDao() {

    }

    public static ServiceDao getInstance() {
        if (instance == null) {
            instance = new ServiceDao();
        }
        return instance;
    }
    @Override
    public ArrayList<Service> getAll() {
        ArrayList<Service> listService = new ArrayList<>();
        try {
            Database db = Database.getInstance();
            Statement stt = db.getStatement();
            String sql = "select * from service";
            ResultSet rs = stt.executeQuery(sql);
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String price = rs.getString("price");
                Service s = new Service(id, name, price);
                listService.add(s);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listService;
    }

    @Override
    public boolean create(Service service) {
        try {
            Database db = Database.getInstance();
            Statement stt = db.getStatement();
            String sql = "insert into service(id,name,price) values('"+service.getId()+"','"+service.getName()+"','"+service.getPrice()+"')";
            if (stt.executeUpdate(sql) > 0) {
                return true;
            }
        } catch (Exception e) {

        }
        return false;
    }

    @Override
    public boolean update(Service service) {
        try {
            Database db = Database.getInstance();
            Statement stt = db.getStatement();
            String sql = "update service set id='" + service.getId() + "', name = '" + service.getName()+ "', price = '" + service.getPrice()+  "'where id= " + service.getId();
            if (stt.executeUpdate(sql) > 0) {
                return true;
            }
        } catch (Exception e) {

        }
        return false;
    }

    @Override
    public boolean delete(Service service) {
        try {
            Database db = Database.getInstance();
            Statement stt = db.getStatement();
            String sql = "delete from service where id= " + service.getId();
            if (stt.executeUpdate(sql) > 0) {
                return true;
            }
        } catch (Exception e) {

        }
        return false;
    }

    @Override
    public Service find(Integer id) {
        return null;
    }

    public Service finds(String id) {
        try {
            Database db = Database.getInstance();
            Statement stt = db.getStatement();
            String sql = "select * from service where id="+id;
            ResultSet rs = stt.executeQuery(sql);
            while (rs.next()) {
                String name = rs.getString("name");
                String price=rs.getString("price");
                Service s = new Service(id, name, price);
                return s;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
