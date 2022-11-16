package main.utils.data;

import main.media.IMovie;
import main.media.ISeries;
import main.user.IUser;

import java.util.ArrayList;

public class DataBaseIO implements IDataIO {
    private ArrayList<IMovie> movies;
    private ArrayList<ISeries> series;
    private ArrayList<IUser> users;

    public ArrayList<IUser> loadUsers() {
        return null;
    }

    public ArrayList<IMovie> loadMovies() {
        return null;
    }

    public ArrayList<ISeries> loadSeries() {
        return null;
    }

    public void save(ArrayList<IUser> users) {

    }
}
