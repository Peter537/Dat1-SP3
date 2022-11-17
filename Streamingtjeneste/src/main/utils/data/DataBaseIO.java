package main.utils.data;

import main.genre.Genre;
import main.genre.IGenre;
import main.media.*;
import main.user.IUser;
import main.utils.data.dbutil.MySQL;
import main.utils.data.dbutil.SQLStatements;

import java.io.File;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

public class DataBaseIO implements IDataIO {

    private final MySQL mySQL;

    public DataBaseIO() {
        mySQL = new MySQL();
        mySQL.openConnection("localhost", "chillmedia", "root", getPassword());
    }

    public ArrayList<IUser> loadUsers() {
        ResultSet userdata = mySQL.executeQuery(SQLStatements.getAllUsers());
        return null;
    }

    public IUser loadUser(String email, String password) {
        return null;
    }

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

    public void saveUser(IUser user) {

    }

    public void saveUser(ArrayList<IUser> users) {

    }

    public MySQL getMySQL() {
        return mySQL;
    }

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
}