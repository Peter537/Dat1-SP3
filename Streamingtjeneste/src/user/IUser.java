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
    void addMovie(IMovie movie);
    void removeMovie(IMovie movie);
    void addWatchedMovie(IMovie movie);
    void save();
}