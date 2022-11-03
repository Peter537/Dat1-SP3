package user;

import java.util.ArrayList;
import media.IMovie;

public interface IUser extends ISaveable {
    int getId();
    String getName();
    String getEmail();
    String getPassword();
    int getAge();
    ArrayList<IMovie> getMyMovies();
    ArrayList<IMovie> getWatchedMovies();
    void addMyMovie(IMovie movie);
    void removeMyMovie(IMovie movie);
    void addWatchedMovie(IMovie movie);
    void save();
}