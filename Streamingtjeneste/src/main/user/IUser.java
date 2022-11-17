package main.user;

import java.util.ArrayList;
import main.media.IMovie;

public interface IUser {

    int getID();

    String getName();

    String getEmail();

    String getPassword();

    int getAge();

    boolean isAdult();

    ArrayList<IMovie> getMyMovies();

    ArrayList<IMovie> getWatchedMovies();

    boolean addToMyMovies(IMovie movie);

    boolean removeFromMyMovies(IMovie movie);

    void addWatchedMovie(IMovie movie);
}