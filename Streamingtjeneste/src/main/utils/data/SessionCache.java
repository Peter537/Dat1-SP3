package main.utils.data;

import main.media.IMovie;
import main.media.ISeries;
import main.user.IUser;

import java.util.ArrayList;

public class SessionCache {

    private final ArrayList<IMovie> movies;
    private final ArrayList<ISeries> series;
    private final ArrayList<IUser> users;

    public SessionCache() {
        this.movies = new ArrayList<>();
        this.series = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public void setMovies(ArrayList<IMovie> movies) {
        this.movies.clear();
        this.movies.addAll(movies);
    }

    public void setSeries(ArrayList<ISeries> series) {
        this.series.clear();
        this.series.addAll(series);
    }

    public void setUsers(ArrayList<IUser> users) {
        this.users.clear();
        this.users.addAll(users);
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

    public boolean addUser(IUser user) {
        if (!this.users.contains(user)) {
            this.users.add(user);
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

    public ArrayList<IUser> getUsers() {
        return users;
    }
}