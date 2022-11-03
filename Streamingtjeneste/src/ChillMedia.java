import genre.IGenre;
import media.IMovie;
import media.ISeries;
import user.IUser;

import java.util.ArrayList;

public class ChillMedia {

    private final ArrayList<IGenre> genres = new ArrayList<>();
    private final ArrayList<IMovie> movies = new ArrayList<>();
    private final ArrayList<ISeries> series = new ArrayList<>();
    private final ArrayList<IUser> users = new ArrayList<>();

    public ChillMedia() {
        load();
    }

    private void load() {

    }

    public void run() {

    }

    private void listMovies() {

    }

    private void listSeries() {

    }

    private ArrayList<IMovie> getPage(int page) {
        return null;
    }

    public ArrayList<IGenre> getGenres() {
        return genres;
    }

    public ArrayList<IMovie> getMovies() {
        return movies;
    }

    public ArrayList<ISeries> getSeries() {
        return series;
    }

    public ArrayList<IUser> getUsers() {
        return users;
    }
}