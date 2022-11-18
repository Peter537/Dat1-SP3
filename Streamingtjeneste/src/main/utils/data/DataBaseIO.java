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

    /**
     * Constructor for DataBaseIO
     * <p>
     * KOMMENTAR_TIL_KONSTRUKTØREN_HER
     */
    public DataBaseIO() {
        mySQL = new MySQL();
        mySQL.openConnection("localhost", "chillmedia", "root", getPassword());
    }

    /**
     *
     *
     * @return ArrayList<IUser>
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
                ArrayList<IMovie> myMovies = new ArrayList<>();
                ArrayList<IMovie> watchedMovies = new ArrayList<>();

                users.add(new User(id, name, email, password, age, myMovies, watchedMovies));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    /**
     *
     *
     * @param email
     * @param password
     * @return IUser
     */
    public IUser loadUser(String email, String password) {
        ResultSet userdata = mySQL.executeQuery(SQLStatements.getUserFromEmailAndPassword(email, password));
        IUser user = null;

        try {
            while (userdata.next()) {
                int id = userdata.getInt("user_id");
                String name = userdata.getString("name");
                int age = userdata.getInt("age");
                ArrayList<IMovie> myMovies = new ArrayList<>();
                ArrayList<IMovie> watchedMovies = new ArrayList<>();

                user = new User(id, name, email, password, age, myMovies, watchedMovies);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    /**
     *
     *
     * @return ArrayList<IMovie>
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
                Movie movie = new Movie(name, rating, genres, year);
                movies.add(movie);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movies;
    }

    /**
     *
     *
     * @return ArrayList<ISeries>
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

                Series serie = new Series(name, startYear, endYear, rating, genres, seasons);
                series.add(serie);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return series;
    }

    /**
     *
     *
     * @param user
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
            }
            else {
                statement = mySQL.getConnection().prepareStatement("UPDATE user SET name = ?, email = ?, password = ?, age = ? WHERE user_id = ?");
                statement.setString(1, user.getName());
                statement.setString(2, user.getEmail());
                statement.setString(3, user.getPassword());
                statement.setInt(4, user.getAge());

                statement.setInt(5, user.getID());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        mySQL.executeChangeQuery(statement);
        mySQL.closeConnection();
    }

    /**
     *
     *
     * @return String
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