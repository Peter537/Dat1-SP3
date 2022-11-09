package user;

import java.util.ArrayList;
import media.IMovie;

public interface IUser {

    int getID();

    String getName();

    String getEmail();

    String getPassword();

    int getAge();

    boolean isAdult();

    ArrayList<IMovie> getMyMovies();

    ArrayList<IMovie> getWatchedMovies();

    boolean addMyMovie(IMovie movie);

    boolean removeMyMovie(IMovie movie);

    void addWatchedMovie(IMovie movie);

    String toJSONString();
}