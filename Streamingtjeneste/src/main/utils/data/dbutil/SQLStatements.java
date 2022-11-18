package main.utils.data.dbutil;

public class SQLStatements {

    /**
     *
     *
     * @return String
     */
    public static String getAllMovies() {
        return "SELECT * FROM chillmedia.movie";
    }

    /**
     *
     *
     * @return String
     */
    public static String getAllSeries() {
        return "SELECT * FROM chillmedia.series";
    }

    /**
     *
     *
     * @return String
     */
    public static String getAllUsers() {
        return "SELECT * FROM chillmedia.user";
    }

    /**
     *
     *
     * @param username
     * @return String
     */
    public static String getUserFromUserName(String username) {
        return "SELECT * FROM chillmedia.user WHERE username = '" + username + "'";
    }

    /**
     *
     *
     * @param email
     * @return String
     */
    public static String getUserFromEmail(String email) {
        return  "SELECT * FROM chillmedia.user WHERE email = '" + email + "'";
    }

    /**
     *
     *
     * @param email
     * @param password
     * @return String
     */
    public static String getUserFromEmailAndPassword(String email, String password) {
        return  "SELECT * FROM chillmedia.user WHERE email = '" + email + "' AND password = '" + password + "'";
    }

    /**
     *
     *
     * @param email
     * @param password
     * @return String
     */
    public static String getMoviesFromUserByEmailAndPassword(String email, String password) {
        return  "SELECT u.user_id, um.um_user_id, um.um_movie_id, um.um_movie_status FROM user_movie AS um JOIN user AS u ON u.user_id = um.um_user_id WHERE u.email = '"+ email +"' AND u.password = '" + password + "' AND u.user_id = um_user_id";
    }

    /**
     *
     *
     * @param id
     * @return String
     */
    public static String getUserFromID(int id) {
        return  "SELECT * FROM chillmedia.user WHERE user_id = '" + id + "'";
    }

    /**
     *
     *
     * @param name
     * @return String
     */
    public static String getMovieFromName(String name) {
        return "SELECT * FROM chillmedia.movie WHERE name LIKE %'" + name +"%'";
    }

    /**
     *
     *
     * @param genre
     * @return String
     */
    public static String getMovieFromGenre(String genre) {
        return "SELECT * FROM chillmedia.movie WHERE genres LIKE = %'" + genre +"'%";
    }

    /**
     *
     *
     * @param minimumRating
     * @return String
     */
    public static String getMovieWithRating(String minimumRating) {
        return "SELECT * FROM chillmedia.movie WHERE rating >= '" + minimumRating +"'";
    }

    /**
     *
     *
     * @param name
     * @return String
     */
    public static String getSeriesFromName(String name) {
        return "SELECT * FROM chillmedia.series WHERE name LIKE %'" + name +"%'";
    }

    /**
     *
     *
     * @param genre
     * @return String
     */
    public static String getSeriesFromGenre(String genre) {
        return "SELECT * FROM chillmedia.series WHERE genres LIKE= %'" + genre +"%'";
    }

    /**
     *
     *
     * @param minimumRating
     * @return String
     */
    public static String getSeriesWithRating(String minimumRating) {
        return "SELECT * FROM chillmedia.series WHERE rating >= '" + minimumRating +"'";
    }
}