package utils.data;

import genre.IGenre;
import genre.MovieGenre;
import genre.SeriesGenre;
import media.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import user.IUser;
import user.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static utils.Query.searchMovieTitleSingle;

public class FileIO implements IDataIO {

    private ArrayList<IMovie> movies;
    private final String userPathJson = "Data/userJson.json";
    private final String userPathCSV = "Data/user.csv";
    private final String moviePath = "Data/film.csv";
    private final String seriesPath = "Data/serier.csv";
    private String userSaveType = "json"; // evt. enum? UserSaveType.JSON, UserSaveType.CSV

    public FileIO() { }

    /*
     * This method loads all users from the file "Data/user.csv" and returns them as an ArrayList.
     *
     * @return ArrayList<IUser> list of users
     */
    @Override
    public ArrayList<IUser> loadUsers() {
        if (userSaveType.equalsIgnoreCase("csv")) {
            return loadUsersFromCSV();
        } else if (userSaveType.equalsIgnoreCase("json")) {
            return loadUsersFromJson();
        } else {
            return null;
        }
    }

    private ArrayList<IUser> loadUsersFromJson() {
        ArrayList<IUser> users = new ArrayList<>();
        if (movies == null) {
            loadMovies();
        }

        try {
            JSONParser parser = new JSONParser();
            JSONArray array = (JSONArray) parser.parse(new FileReader(userPathJson));

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

    private ArrayList<IUser> loadUsersFromCSV() {
        File file = new File(userPathCSV);
        ArrayList<IUser> users = new ArrayList<>();

        try {
            Scanner readUsers = new Scanner(file);

            while (readUsers.hasNextLine()) {
                String line = readUsers.nextLine();
                String[] values = line.split(";");

                int id = Integer.parseInt(values[0].trim());
                String name = values[1];
                String email = values[2].trim();
                String password = values[3].trim();
                int age = Integer.parseInt(values[4].trim());

                users.add(new User(id, name, email, password, age, new ArrayList<>(), new ArrayList<>()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    /*
     * This method loads all movies from the file "Data/film.csv" and returns them as an ArrayList.
     *
     * @return ArrayList<IMovie> list of movies
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

            while (readMovies.hasNextLine()) {
                String line = readMovies.nextLine();
                String[] values = line.split(";");

                String movieTitles = values[0];

                int year = Integer.parseInt(values[1].trim());

                ArrayList<IGenre> movieGenres = new ArrayList<>();
                String[] genreTitles = values[2].trim().split(",");

                for (String genre : genreTitles) {
                    String genreName = genre.trim().toUpperCase().replaceAll("-", "_");
                    if (Arrays.stream(MovieGenre.values()).anyMatch(g -> g.name().equals(genreName))) {
                        movieGenres.add(MovieGenre.valueOf(genreName));
                    }
                }

                float rating = Float.parseFloat(values[3].replace(',', '.').trim());
                movieData.add(new Movie(movieTitles, rating, movieGenres, year));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        movies = movieData;
        return movieData;
    }

    /*
     * This method loads all series from the file "Data/series.csv" and returns them as an ArrayList.
     *
     * @return ArrayList<ISeries> list of series
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
                    if (Arrays.stream(SeriesGenre.values()).anyMatch(g -> g.name().equals(genreName))) {
                        seriesGenre.add(SeriesGenre.valueOf(genreName));
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

    public void save(ArrayList<IUser> users) {
        if (userSaveType.equalsIgnoreCase("csv")) {
            saveAsCSV(users);
        } else if (userSaveType.equalsIgnoreCase("json")) {
            saveAsJson(users);
        }
    }

    private void saveAsCSV(ArrayList<IUser> users) {
        try {
            FileWriter writer = new FileWriter(userPathCSV);
            for (IUser user : users) {
                String myMovies = user.getMyMovies().toString().replaceAll("\\[", "").replaceAll("\\]", "");
                String watchedMovies = user.getWatchedMovies().toString().replaceAll("\\[", "").replaceAll("\\]", "");
                writer.write(user.getID() + ";" + user.getName() + ";" + user.getEmail() + ";" + user.getPassword() + ";" + user.getAge() + ";" + myMovies + ";" + watchedMovies +"\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveAsJson(ArrayList<IUser> users) {
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
            FileWriter file = new FileWriter(userPathJson);
            file.write(userArray.toString());
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}