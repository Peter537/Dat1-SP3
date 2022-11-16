package main.utils.data.dbutil;

import java.sql.*;

public class MySQL {

    private Connection connection;

    public boolean openConnection(String url, String name, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Example: "jdbc:mysql://localhost:/world", "root", "12321"
            connection = DriverManager.getConnection("jdbc:mysql://" + url + "?autoReconnect=true&useSSL=false", name, password);
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean openConnection(String IP, String Schema, String name, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Example: "jdbc:mysql://localhost:/world", "root", "12321"
            connection = DriverManager.getConnection("jdbc:mysql://" + IP + ":/" + Schema + "?autoReconnect=true&useSSL=false", name, password);
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean closeConnection() {
        try {
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ResultSet executeQuery(String query) {
        try {
            return connection.createStatement().executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean executeChangeQuery(PreparedStatement query) {
        try {
            query.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Connection getConnection() {
        return connection;
    }
}