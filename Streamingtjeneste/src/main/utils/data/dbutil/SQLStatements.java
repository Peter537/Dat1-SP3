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
     * NOT IN USE, NOT IN UML
     *
     * returns the user queried by username
     *
     * @param username
     * @return String
     */
    public static String getUserFromUserName(String username) {
        return "SELECT * FROM chillmedia.user WHERE username = '" + username + "'";
    }

    /**
     * NOT IN USE, NOT IN UML
     *
     * @param email
     * @return String
     */
    public static String getUserFromEmail(String email) {
        return  "SELECT * FROM chillmedia.user WHERE email = '" + email + "'";
    }

    /**
     * queries the SQL database for a user by email and passowrd
     *
     * @param email the inputted email
     * @param password the inputted password
     * @return String returns the user associated with the matching email and password
     */
    public static String getUserFromEmailAndPassword(String email, String password) {
        return  "SELECT * FROM chillmedia.user WHERE email = '" + email + "' AND password = '" + password + "'";
    }

    /**
     *This method gets the user's saved movies by their email and passowrd
     *
     * @param email the inputted email
     * @param password the inputted password
     * @return String returns the user associated with the matching email and password
     */
    public static String getMoviesFromUserByEmailAndPassword(String email, String password) {
        return  "SELECT u.user_id, um.um_user_id, um.um_movie_id, um.um_movie_status FROM user_movie AS um JOIN user AS u ON u.user_id = um.um_user_id WHERE u.email = '"+ email +"' AND u.password = '" + password + "' AND u.user_id = um_user_id";
    }

    /**
     * NOT IN USE, NOT IN UML
     *
     * @param id
     * @return String
     */
    public static String getUserFromID(int id) {
        return  "SELECT * FROM chillmedia.user WHERE user_id = '" + id + "'";
    }

    /**
     * NOT IN USE, NOT IN UML
     *
     * @param name
     * @return String
     */
    public static String getMovieFromName(String name) {
        return "SELECT * FROM chillmedia.movie WHERE name LIKE %'" + name +"%'";
    }

    /**
     * NOT IN USE, NOT IN UML
     *
     * @param genre
     * @return String
     */
    public static String getMovieFromGenre(String genre) {
        return "SELECT * FROM chillmedia.movie WHERE genres LIKE = %'" + genre +"'%";
    }

    /**
     * NOT IN USE, NOT IN UML
     *
     * @param minimumRating
     * @return String
     */
    public static String getMovieWithRating(String minimumRating) {
        return "SELECT * FROM chillmedia.movie WHERE rating >= '" + minimumRating +"'";
    }

    /**
     * NOT IN USE, NOT IN UML
     *
     * @param name
     * @return String
     */
    public static String getSeriesFromName(String name) {
        return "SELECT * FROM chillmedia.series WHERE name LIKE %'" + name +"%'";
    }

    /**
     * NOT IN USE, NOT IN UML
     *
     * @param genre
     * @return String
     */
    public static String getSeriesFromGenre(String genre) {
        return "SELECT * FROM chillmedia.series WHERE genres LIKE= %'" + genre +"%'";
    }

    /**
     * NOT IN USE, NOT IN UML
     *
     * @param minimumRating
     * @return String
     */
    public static String getSeriesWithRating(String minimumRating) {
        return "SELECT * FROM chillmedia.series WHERE rating >= '" + minimumRating +"'";
    }
}