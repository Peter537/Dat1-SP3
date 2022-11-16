package main.utils.data;

import main.media.IMovie;
import main.media.ISeries;
import main.user.IUser;

import java.util.ArrayList;

public class SessionCache {

    private final ArrayList<IMovie> movies = new ArrayList<>();
    private final ArrayList<ISeries> series = new ArrayList<>();
    private IUser user;

    public SessionCache() { }

    public void setMovies(ArrayList<IMovie> movies) {
        this.movies.clear();
        this.movies.addAll(movies);
    }

    public void setSeries(ArrayList<ISeries> series) {
        this.series.clear();
        this.series.addAll(series);
    }

    public void setUser(IUser user) {
        this.user = user;
    }

    public boolean addMovie(IMovie movie) {
        if (!this.movies.contains(movie)) {
            this.movies.add(movie);
            return true;
        }
        return false;
    }

    public boolean addSeries(ISeries series) {
        if (!this.series.contains(series)) {
            this.series.add(series);
            return true;
        }
        return false;
    }

    public ArrayList<IMovie> getMovies() {
        return movies;
    }

    public ArrayList<ISeries> getSeries() {
        return series;
    }

    public IUser getUser() {
        return user;
    }
}