package main.utils.data;

import main.genre.Genre;
import main.genre.IGenre;
import main.media.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import main.user.IUser;
import main.user.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static main.utils.Query.searchMovieTitleSingle;

public class FileIO implements IDataIO {

    private ArrayList<IMovie> movies;
    private final String userPath = "Data/user.json";
    private final String moviePath = "Data/film.csv";
    private final String seriesPath = "Data/serier.csv";

    /**
     * Constructor for FileIO
     * <p>
     * KOMMENTAR_TIL_KONSTRUKTØREN_HER
     */
    public FileIO() { }

    /**
     *
     *
     * @return ArrayList<IUser>
     */
    @Override
    public ArrayList<IUser> loadUsers() {
        return loadUsersFromJson();
    }

    /**
     *
     *
     * @param email
     * @param password
     * @return IUser
     */
    public IUser loadUser(String email, String password) {
        /* TODO: Skal finde ud af om det er bedre at bruge en for loop eller en stream her
        ArrayList<IUser> users = loadUsers();
        for (IUser user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
         */
        return null;
    }

    /**
     *
     *
     * @return ArrayList<IUser>
     */
    private ArrayList<IUser> loadUsersFromJson() {
        ArrayList<IUser> users = new ArrayList<>();
        if (movies == null) {
            loadMovies();
        }

        try {
            JSONParser parser = new JSONParser();
            JSONArray array = (JSONArray) parser.parse(new FileReader(userPath));

            for (Object object : array) {
                JSONObject user = (JSONObject) object;
                String userName = (String) user.get("Name");
                String email = (String) user.get("Email");
                String password = (String) user.get("Password");
                int age = Integer.parseInt(String.valueOf(user.get("Age")));
                int ID = Integer.parseInt(String.valueOf(user.get("ID")));

                ArrayList<String> myMoviesString = (ArrayList<String>) user.get("WatchList");
                ArrayList<IMovie> myMovies = new ArrayList<>();
                ArrayList<String> myWatchedMoviesString = (ArrayList<String>) user.get("SeenList");
                ArrayList<IMovie> myWatchedMovies = new ArrayList<>();

                for (String s : myMoviesString) {
                    myMovies.add(searchMovieTitleSingle(movies, s));
                }

                for (String s : myWatchedMoviesString) {
                    myWatchedMovies.add(searchMovieTitleSingle(movies, s));
                }

                IUser u = new User(ID, userName, email, password, age, myMovies, myWatchedMovies);
                users.add(u);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return users;
    }

    /**
     *
     *
     * @return ArrayList<IMovie>
     */
    @Override
    public ArrayList<IMovie> loadMovies() {
        if (movies != null) {
            return movies;
        }
        File file = new File(moviePath);
        ArrayList<IMovie> movieData = new ArrayList<>();

        try {
            Scanner readMovies = new Scanner(file);
            int id = 0;

            while (readMovies.hasNextLine()) {
                String line = readMovies.nextLine();
                String[] values = line.split(";");

                String movieTitles = values[0];

                int year = Integer.parseInt(values[1].trim());

                ArrayList<IGenre> movieGenres = new ArrayList<>();
                String[] genreTitles = values[2].trim().split(",");

                for (String genre : genreTitles) {
                    String genreName = genre.trim().toUpperCase().replaceAll("-", "_");
                    if (Arrays.stream(Genre.values()).anyMatch(g -> g.isMovieGenre() && g.name().equals(genreName))) {
                        movieGenres.add(Genre.valueOf(genreName));
                    }
                }

                float rating = Float.parseFloat(values[3].replace(',', '.').trim());
                movieData.add(new Movie(id, movieTitles, rating, movieGenres, year));
                id++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        movies = movieData;
        return movieData;
    }

    /**
     *
     *
     * @return ArrayList<ISeries>
     */
    @Override
    public ArrayList<ISeries> loadSeries() {
        File file = new File(seriesPath);
        ArrayList<ISeries> seriesData = new ArrayList<>();

        try {
            Scanner readSeries = new Scanner(file);

            while (readSeries.hasNextLine()) {
                String line = readSeries.nextLine();
                String [] values = line.split(";");

                String title = values[0];

                String[] yearsString =(values[1].split("-"));
                int startYear = Integer.parseInt(yearsString[0].trim());

                int endYear = -1;

                if ( yearsString.length > 1 && !yearsString[1].trim().equals("")) {
                    endYear = Integer.parseInt(yearsString[1].trim());
                }

                ArrayList<IGenre> seriesGenre = new ArrayList<>();
                String[] genreTitles = values[2].trim().split(",");

                for (String genre : genreTitles) {
                    String genreName = genre.trim().toUpperCase().replaceAll("-", "_");
                    if (Arrays.stream(Genre.values()).anyMatch(g -> g.isSeriesGenre() && g.name().equals(genreName))) {
                        seriesGenre.add(Genre.valueOf(genreName));
                    }
                }

                float rating = Float.parseFloat(values[3].replaceAll(",", "."));

                ArrayList<Season> seasonsEpisodes = new ArrayList<>();
                String[] seasonValues = values[4].split(",");

                for (String season : seasonValues) {
                    String[] seasonSplit = season.trim().split("-");
                    int seasonCount = Integer.parseInt(seasonSplit[0]);
                    int episodeCount = Integer.parseInt(seasonSplit[1]);

                    Season thisSeason = new Season (seasonCount,episodeCount);
                    seasonsEpisodes.add(thisSeason);
                }
                seriesData.add(new Series(title, startYear, endYear, rating, seriesGenre, seasonsEpisodes));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return seriesData;
    }

    /**
     *
     *
     * @param user
     */
    public void saveUser(IUser user) {
        saveAsJson(user);
    }

    /**
     *
     *
     * @param user
     */
    private void saveAsJson(IUser user) {
        ArrayList<IUser> users = loadUsersFromJson();
        users.add(user);
        JSONArray userArray = new JSONArray();

        for (IUser u : users) {
            JSONObject userObject = new JSONObject();

            userObject.put("ID", u.getID());
            userObject.put("Name", u.getName());
            userObject.put("Email", u.getEmail());
            userObject.put("Password", u.getPassword());
            userObject.put("Age", u.getAge());
            ArrayList<String> myMovies = new ArrayList<>();
            ArrayList<String> watchedMovies = new ArrayList<>();
            for (IMovie m : u.getMyMovies()) {
                myMovies.add(m.getTitle());
            }
            for (IMovie m : u.getWatchedMovies()) {
                watchedMovies.add(m.getTitle());
            }
            userObject.put("WatchList", myMovies);
            userObject.put("SeenList", watchedMovies);

            userArray.add(userObject);
        }
        try {
            FileWriter file = new FileWriter(userPath);
            file.write(userArray.toString());
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setCache(IUser user) {
        // nothing to do here
    }
}