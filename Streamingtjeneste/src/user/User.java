package user;

import user.IUser;

import java.util.ArrayList;
import media.IMovie;

public class User implements IUser {
    private static int idCounter = 0;
    private int id;
    private String name;
    private String password;
    private String email;
    private int age;
    private final ArrayList<IMovie> myMovies = new ArrayList<>();
    private final ArrayList<IMovie> watchedMovies = new ArrayList<>();

    public User(String name, String password, String email, int age) {
        this.id = idCounter++;
        this.name = name;
        this.password = password;
        this.email = email;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getAge() {
        return age;
    }

    public ArrayList<IMovie> getMyMovies() {
        return myMovies;
    }

    public ArrayList<IMovie> getWatchedMovies() {
        return watchedMovies;
    }

    public void addMovie(IMovie movie) {
        myMovies.add(movie);
    }

    public void removeMovie(IMovie movie) {
        myMovies.remove(movie);
    }

    public void addWatchedMovie(IMovie movie) {
        watchedMovies.add(movie);
    }

    public void save() {
        saveWatchedMovies();
        saveMyMovies();
    }

    private void saveWatchedMovies() {

    }

    private void saveMyMovies() {

    }
}
