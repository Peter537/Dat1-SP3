package main.utils.data.dbutil;

import java.sql.*;

public class MySQL {

    private Connection connection;

    /**
     * Constructor for MySQL
     * <p>
     * KOMMENTAR_TIL_KONSTRUKTØREN_HER
     */
    public MySQL() { }

    /**
     *
     *
     * Example of how to use this method:
     * openConnection("localhost:/world", "root", "12321");
     *
     * @param url
     * @param name
     * @param password
     * @return boolean
     */
    public boolean openConnection(String url, String name, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://" + url + "?autoReconnect=true&useSSL=false", name, password);
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *
     *
     * Example of how to use this method:
     * openConnection("localhost", "world", "root", "12321");
     *
     * @param ip
     * @param schema
     * @param name
     * @param password
     * @return boolean
     */
    public boolean openConnection(String ip, String schema, String name, String password) {
        return openConnection(ip + ":/" + schema, name, password);
    }

    /**
     *
     *
     * @return boolean
     */
    public boolean closeConnection() {
        try {
            getConnection().close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *
     *
     * @param query
     * @return ResultSet
     */
    public ResultSet executeQuery(String query) {
        try {
            return connection.createStatement().executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     *
     * @param query
     * @return boolean
     */
    public boolean executeChangeQuery(PreparedStatement query) {
        try {
            query.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * NOTE: This method is not tested yet, and it's not in the UML yet
     *
     * @param p
     * @param startIndex
     * @param strings
     * @return PreparedStatement
     */
    public PreparedStatement setStrings(PreparedStatement p, int startIndex, String... strings) {
        try {
            for (int i = 0; i < strings.length; i++) {
                p.setString(startIndex + i, strings[i]);
            }
            return p;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * NOTE: This method is not tested yet, and it's not in the UML yet
     *
     * @param p
     * @param startIndex
     * @param ints
     * @return PreparedStatement
     */
    public PreparedStatement setInts(PreparedStatement p, int startIndex, int... ints) {
        try {
            for (int i = 0; i < ints.length; i++) {
                p.setInt(startIndex + i, ints[i]);
            }
            return p;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Connection getConnection() {
        return connection;
    }
}