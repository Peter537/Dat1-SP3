package main.utils.data.dbutil;

public class SQLStatements {
    public static String getAllMovies() {
        return "SELECT * FROM chillmedia.movie";
    }

    public static String getAllSeries() {
        return "SELECT * FROM chillmedia.series";
    }

    public static String getAllUsers() {
        return "SELECT * FROM chillmedia.user";
    }

    public static String getUser(String username) {
        return "SELECT * FROM chillmedia.user WHERE username = '" + username + "'";
    }
}
