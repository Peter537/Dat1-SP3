package main.utils.data;

import main.media.IMovie;
import main.media.ISeries;
import main.user.IUser;

import java.util.ArrayList;

public class SessionCache {

    private final ArrayList<IMovie> movies;
    private final ArrayList<ISeries> series;
    private final ArrayList<IUser> users;

    public SessionCache(ArrayList<IMovie> movie, ArrayList<ISeries> series, ArrayList<IUser> users) {
        this.movies = movie;
        this.series = series;
        this.users = users;
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