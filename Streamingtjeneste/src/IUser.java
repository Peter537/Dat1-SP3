import java.util.ArrayList;

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
//
//    private void saveWatchedMovies() {}
//
//    private void saveMyMovies(){}
}
