package main.utils.data;

import main.media.IMovie;
import main.media.ISeries;
import main.user.IUser;

import java.util.ArrayList;

public class SessionCache {

    private final ArrayList<IMovie> movies = new ArrayList<>();
    private final ArrayList<ISeries> series = new ArrayList<>();
    private IUser user;

    /**
     * Constructor for SessionCache
     * <p>
     * This constructor is empty
     */
    public SessionCache() { }

    public void setMovies(ArrayList<IMovie> movies) {
        getMovies().clear();
        getMovies().addAll(movies);
    }

    public void setSeries(ArrayList<ISeries> series) {
        getSeries().clear();
        getSeries().addAll(series);
    }

    public void setUser(IUser user) {
        this.user = user;
    }

    public ArrayList<IMovie> getMovies() {
        return this.movies;
    }

    public ArrayList<ISeries> getSeries() {
        return this.series;
    }

    public IUser getUser() {
        return this.user;
    }
}