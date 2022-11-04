package user;

import java.util.ArrayList;
import media.IMovie;

public interface IUser {
    int getId();
    String getName();
    String getEmail();
    String getPassword();
    int getAge();
    ArrayList<IMovie> getMyMovies();
    ArrayList<IMovie> getWatchedMovies();
    boolean addMyMovie(IMovie movie);
    boolean removeMyMovie(IMovie movie);
    void addWatchedMovie(IMovie movie);
    void save(ArrayList<IUser> users);
}