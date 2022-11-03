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

    public User(int ID, String name, String password, String email, int age) {
        if (ID != -1)
            this.id = ID;
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

    public boolean addMyMovie(IMovie movie) {
        if (!myMovies.contains(movie)) {
            myMovies.add(movie);
            return true;
        }
        return false;
    }

    public boolean removeMyMovie(IMovie movie) {
        if (myMovies.contains(movie)) {
            myMovies.remove(movie);
            return true;
        }
        return false;
    }

    public void addWatchedMovie(IMovie movie) {
        if (!watchedMovies.contains(movie)) {
            watchedMovies.add(movie);
        }
    }

    public String toString() {
        return "Name: " + name + " Email: " + email;
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
