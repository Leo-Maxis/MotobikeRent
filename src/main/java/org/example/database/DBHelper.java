package org.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
    public static final String DB_NAME = "Motobike";
    public static final String USERNAME = "sa";
    public static final String PASSWORD ="123123qwe";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        return getConnection(DB_NAME, USERNAME, PASSWORD);
    }

    public static Connection getConnection(String dbName, String username, String password) throws SQLException, ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String dbURL = "jdbc:sqlserver://localhost:1433;database=%s;username=%s;password=%s;encrypt=true;trustServerCertificate=true;";
        Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=Motobike;username=sa;password=123123qwe;encrypt=true;trustServerCertificate=true;");
        return conn;
    }
}
