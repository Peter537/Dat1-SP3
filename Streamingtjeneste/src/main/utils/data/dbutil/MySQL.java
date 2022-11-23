package main.utils.data.dbutil;

import java.sql.*;

public class MySQL {

    private Connection connection;

    /**
     * Constructor for MySQL
     * <p>
     * This constructor is left empty to show that it does not do anything within the constructor
     */
    public MySQL() { }

    /**
     * This method establishes a connection to the database
     *
     * Example of how to use this method:
     * openConnection("localhost:/world", "root", "12321");
     *
     * @param url is the unique url of our database
     * @param name is the name of the database
     * @param password is the password of the database
     * @return boolean returns there a connection was succesfully estrablished or not
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
     * This is an overloaded method that an opposed to using a url, uses an ip and a local schema to establish a connection
     *
     * Example of how to use this method:
     * openConnection("localhost", "world", "root", "12321");
     *
     * @param ip is the ip inputted
     * @param schema is the schema name inputted
     * @param name is the database name
     * @param password is the database password
     * @return boolean returns true if a connection was established
     */
    public boolean openConnection(String ip, String schema, String name, String password) {
        return openConnection(ip + ":/" + schema, name, password);
    }

    /**
     * closes a connection manually before closing the program as a common courtesy.
     *
     * @return boolean returns true if connection was closed successfully
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
     * This method lets the user query within the database (which contains all loaded/saved data)
     *
     * @param query is the string value the user inputted
     * @return ResultSet returns the results of the query
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
     * This method is similar to the above executeQuery, however this method used prepared statements so no human
     * erros can be made when changing and handling the data on the database
     *
     * @param query is the prepared statement inputted
     * @return boolean returns true if the prepared statement was executed properly
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

    public Connection getConnection() {
        return connection;
    }
}