package main.utils.data.dbutil;

public class SQLStatements {

    /**
     * @return String returns all movies from the database
     */
    public static String getAllMovies() {
        return "SELECT * FROM chillmedia.movie";
    }

    /**
     * @return String returns all series from the database
     */
    public static String getAllSeries() {
        return "SELECT * FROM chillmedia.series";
    }

    /**
     * @return String returns all users loaded from the database
     */
    public static String getAllUsers() {
        return "SELECT * FROM chillmedia.user";
    }

    /**
     * queries the SQL database for a user by email and passowrd
     *
     * @param email the inputted email
     * @param password the inputted password
     * @return String returns the user associated with the matching email and password
     */
    public static String getUserFromEmailAndPassword(String email, String password) {
        return "SELECT * FROM chillmedia.user WHERE email = '" + email + "' AND password = '" + password + "'";
    }

    /**
     *This method gets the user's saved movies by their email and passowrd
     *
     * @param email the inputted email
     * @param password the inputted password
     * @return String returns the user associated with the matching email and password
     */
    public static String getMoviesFromUserByEmailAndPassword(String email, String password) {
        return "SELECT u.user_id, um.um_user_id, um.um_movie_id, um.um_movie_status FROM user_movie AS um JOIN user AS u ON u.user_id = um.um_user_id WHERE u.email = '"+ email +"' AND u.password = '" + password + "' AND u.user_id = um_user_id";
    }
}