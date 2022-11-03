package utils.data;

import media.IMovie;
import media.ISeries;
import user.IUser;

import java.util.ArrayList;

public interface IDataIO {

    ArrayList<IUser> loadUsers();
    ArrayList<IMovie> loadMovies();
    ArrayList<ISeries> loadSeries();
}