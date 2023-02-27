package database;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Database {
    private static Database instance;
    private Statement statement;

    private Database() throws Exception {

        FileInputStream fis = new FileInputStream("config.txt");
        DataInputStream dis = new DataInputStream(fis);
        Class.forName(dis.readLine());
        String connectionString = dis.readLine();
        String user = dis.readLine();
        String password = dis.readLine();
        Connection conn = DriverManager.getConnection(connectionString, user, password);
        this.statement = conn.createStatement();
    }

    public static Database getInstance() throws Exception{
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }


    public Statement getStatement() {
        return statement;
    }

    public Database setStatement(Statement statement) {
        this.statement = statement;
        return this;
    }
}
