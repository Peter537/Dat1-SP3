package main.utils.data;

import main.media.IMovie;
import main.media.ISeries;
import main.user.IUser;

import java.util.ArrayList;

public interface IDataIO {

    ArrayList<IUser> loadUsers(); // TODO: Not sure om denne skal være privat

    IUser loadUser(String email, String password);

    ArrayList<IMovie> loadMovies();

    ArrayList<ISeries> loadSeries();

    void saveUser(IUser user);

    void setCache(IUser user);
}