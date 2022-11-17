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

    public static String getUserFromUserName(String username) {
        return "SELECT * FROM chillmedia.user WHERE username = '" + username + "'";
    }
    public static String getUserFromEmail(String email)
    {
        return  "SELECT * FROM chillmedia.user WHERE email = '" + email + "'";
    }





    public static String getMovieFromName (String name)
    {
        return "SELECT * FROM chillmedia.movie WHERE name LIKE '" + name +"%'";
    }

    public static String getMovieFromGenre (String genre)
    {
        return "SELECT * FROM chillmedia.movie WHERE genres = '" + genre +"'";
    }

    public static String getMovieWithRating (String rating)
    {
        return "SELECT * FROM chillmedia.movie WHERE rating >= '" + rating +"'";
    }




public static String getSeriesFromName (String name)
{
    return "SELECT * FROM chillmedia.series WHERE name LIKE '" + name +"%'";
}

public static String getSeriesFromGenre (String genre)
{
    return "SELECT * FROM chillmedia.series WHERE genres = '" + genre +"'";
}

public static String getSeriesWithRating (String rating)
{
    return "SELECT * FROM chillmedia.series WHERE rating >= '" + rating +"'";
}




}
