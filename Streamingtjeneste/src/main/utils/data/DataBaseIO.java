package main.utils.data;

import main.genre.Genre;
import main.genre.IGenre;
import main.media.*;
import main.user.IUser;
import main.user.User;
import main.utils.data.dbutil.MySQL;
import main.utils.data.dbutil.SQLStatements;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

public class DataBaseIO implements IDataIO {

    private final MySQL mySQL;
    private ArrayList<IMovie> myMoviesCached;
    private ArrayList<IMovie> watchedMoviesCached;

    /**
     * Constructor for DataBaseIO
     * <p>
     * This constructor establishes a connection to the database
     */
    public DataBaseIO() {
        mySQL = new MySQL();
        mySQL.openConnection("localhost", "chillmedia", "root", getPassword());
    }

    /**
     * This method loads all users from the SQL database
     *
     * @return ArrayList<IUser> returns an arraylist of user objects
     */
    public ArrayList<IUser> loadUsers() {
        ResultSet userdata = mySQL.executeQuery(SQLStatements.getAllUsers());
        ArrayList<IUser> users = new ArrayList<>();
        try {
            while (userdata.next()) {
                int id = userdata.getInt("user_id");
                String name = userdata.getString("name");
                String email = userdata.getString("email");
                String password = userdata.getString("password");
                int age = userdata.getInt("age");
                ResultSet userMovies = mySQL.executeQuery(SQLStatements.getMoviesFromUserByEmailAndPassword(email, password));
                ArrayList<IMovie> myMovies = new ArrayList<>();
                ArrayList<IMovie> watchedMovies = new ArrayList<>();
                while (userMovies.next()) {
                    ArrayList<IMovie> allMovies = loadMovies();
                    for (IMovie movie : allMovies) {
                        if (movie.getID() == userMovies.getInt("um_movie_id")) {
                            if (userMovies.getString("um_movie_status").equals("WATCHED")) {
                                watchedMovies.add(movie);
                            } else {
                                myMovies.add(movie);
                            }
                        }
                    }
                }
                users.add(new User(id, name, email, password, age, myMovies, watchedMovies));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }
    
    /**
     *This method loads all movies from the SQL database
     *
     * @return ArrayList<IMovie> returns an arraylist of movie objects
     */
    public ArrayList<IMovie> loadMovies() {
        ResultSet movieData = mySQL.executeQuery(SQLStatements.getAllMovies());
        ArrayList<IMovie> movies = new ArrayList<>();
        try {
            while (movieData.next()) {
                String name = movieData.getString("name");
                float rating = movieData.getFloat("rating");
                ArrayList<IGenre> genres = new ArrayList<>();
                String[] genreStrings = movieData.getString("genres").split(",");
                for (String genreString : genreStrings) {
                    genres.add(Genre.valueOf(genreString));
                }
                int year = movieData.getInt("year_of_filming");
                int id = movieData.getInt("movie_id");
                movies.add(new Movie(id, name, rating, genres, year));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movies;
    }

    /**
     * This method loads all series from the SQL database
     *
     * @return ArrayList<ISeries> returns an arralist of series objects
     */
    public ArrayList<ISeries> loadSeries() {
        ResultSet seriesData = mySQL.executeQuery(SQLStatements.getAllSeries());
        ArrayList<ISeries> series = new ArrayList<>();
        try {
            while (seriesData.next()) {
                String name = seriesData.getString("name");
                int startYear = seriesData.getInt("start_year");
                int endYear = seriesData.getInt("end_year");
                float rating = seriesData.getFloat("rating");
                int id = seriesData.getInt("series_id");
                ArrayList<IGenre> genres = new ArrayList<>();
                String[] genreStrings = seriesData.getString("genres").split(",");
                for (String genreString : genreStrings) {
                    genres.add(Genre.valueOf(genreString));
                }
                ArrayList<Season> seasons = new ArrayList<>();
                String[] seasonStrings = seriesData.getString("seasons").split(",");
                for (String seasonString : seasonStrings) {
                    seasons.add(new Season(Integer.parseInt(seasonString.split("-")[0]), Integer.parseInt(seasonString.split("-")[1])));
                }
                series.add(new Series(id, name, startYear, endYear, rating, genres, seasons));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return series;
    }

    /**
     * this method saves a user's data.
     *
     * @param user is the user object referenced
     */
    public void saveUser(IUser user) {
        PreparedStatement statement = null;
        try {
            if (user.getID() == -1) {
                statement = mySQL.getConnection().prepareStatement("INSERT INTO user(name, email, password, age) VALUES (?, ?, ?, ?)");
                statement.setString(1, user.getName());
                statement.setString(2, user.getEmail());
                statement.setString(3, user.getPassword());
                statement.setInt(4, user.getAge());
            } else {
                statement = mySQL.getConnection().prepareStatement("UPDATE user SET name = ?, email = ?, password = ?, age = ? WHERE user_id = ?");
                statement.setString(1, user.getName());
                statement.setString(2, user.getEmail());
                statement.setString(3, user.getPassword());
                statement.setInt(4, user.getAge());
                statement.setInt(5, user.getID());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        mySQL.executeChangeQuery(statement);
        saveMoviesToUser(user);
        mySQL.closeConnection();
    }

    /**
     * This method saves the "My movies" tab to the active user.
     *
     * @param user is the active user object
     */
    private void saveMoviesToUser(IUser user) {
        ResultSet rs = mySQL.executeQuery(SQLStatements.getUserFromEmailAndPassword(user.getEmail(), user.getPassword()));
        PreparedStatement statement;
        try {
            while (rs.next()) {
                int id = rs.getInt("user_id");
                for (IMovie movie : user.getWatchedMovies()) {
                    if (!watchedMoviesCached.contains(movie)) {
                        statement = mySQL.getConnection().prepareStatement("INSERT INTO user_movie(um_user_id, um_movie_id, um_movie_status) VALUES (?, ?, ?)");
                        statement.setInt(1, id);
                        statement.setInt(2, movie.getID());
                        if (user.getMyMovies().contains(movie)) {
                            statement.setString(3, "WATCHED,WANTTO");
                            user.removeFromMyMovies(movie);
                        } else {
                            statement.setString(3, "WATCHED");
                        }
                        mySQL.executeChangeQuery(statement);
                    }
                }
                for (IMovie movie : watchedMoviesCached) {
                    if (!user.getWatchedMovies().contains(movie)) {
                        statement = mySQL.getConnection().prepareStatement("DELETE FROM user_movie WHERE um_user_id = ? AND um_movie_id = ?, um_movie_status = ?");
                        statement.setInt(1, id);
                        statement.setInt(2, movie.getID());
                        statement.setString(3, "WATCHED");
                        mySQL.executeChangeQuery(statement);
                    }
                }
                for (IMovie movie : user.getMyMovies()) {
                    if (!myMoviesCached.contains(movie)) {
                        statement = mySQL.getConnection().prepareStatement("INSERT INTO user_movie(um_user_id, um_movie_id, um_movie_status) VALUES (?, ?, ?)");
                        statement.setInt(1, id);
                        statement.setInt(2, movie.getID());
                        statement.setString(3, "WANTTO");
                        mySQL.executeChangeQuery(statement);
                    }
                }
                for (IMovie movie : myMoviesCached) {
                    if (!user.getMyMovies().contains(movie)) {
                        statement = mySQL.getConnection().prepareStatement("DELETE FROM user_movie WHERE um_user_id = ? AND um_movie_id = ?");
                        statement.setInt(1, id);
                        statement.setInt(2, movie.getID());
//                        statement.setString(3, "WANTTO");
                        mySQL.executeChangeQuery(statement);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads watched movies and my movies before the user can access them
     *
     * @param user is the active user object
     */
    public void setCache(IUser user) {
        this.watchedMoviesCached = (ArrayList<IMovie>) user.getWatchedMovies().clone();
        this.myMoviesCached = (ArrayList<IMovie>) user.getMyMovies().clone();
    }

    /**
     * This method returns the password (or any text) written in the referenced textfile
     *
     * @return String returns the password written in the textfile as a string
     */
    private String getPassword() {
        File file = new File("Data/dbpass.csv");
        try {
            Scanner scanner = new Scanner(file);
            return scanner.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public MySQL getMySQL() {
        return mySQL;
    }
}