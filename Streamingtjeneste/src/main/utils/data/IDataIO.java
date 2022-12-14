package main.utils.data;

import main.media.IMovie;
import main.media.ISeries;
import main.user.IUser;

import java.util.ArrayList;

public interface IDataIO {

    ArrayList<IUser> loadUsers();

    ArrayList<IMovie> loadMovies();

    ArrayList<ISeries> loadSeries();

    void saveUser(IUser user);

    void setCache(IUser user);
}