package main.user;

import java.util.ArrayList;
import main.media.IMovie;

public class User implements IUser {

    private final int id;
    private final String name;
    private final String email;
    private final String password;
    private final int age;
    private final ArrayList<IMovie> myMovies;
    private final ArrayList<IMovie> watchedMovies;

    /**
     * Constructor for User
     * <p>
     * KOMMENTAR_TIL_KONSTRUKTØREN_HER
     *
     * @param id
     * @param name
     * @param email
     * @param password
     * @param age
     * @param myMovies
     * @param watchedMovies
     */
    public User(int id, String name, String email, String password, int age, ArrayList<IMovie> myMovies, ArrayList<IMovie> watchedMovies) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.myMovies = myMovies;
        this.watchedMovies = watchedMovies;
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public int getAge() {
        return this.age;
    }

    @Override
    public boolean isAdult() {
        return getAge() >= 18;
    }

    @Override
    public ArrayList<IMovie> getMyMovies() {
        return this.myMovies;
    }

    @Override
    public ArrayList<IMovie> getWatchedMovies() {
        return this.watchedMovies;
    }

    @Override
    public boolean addToMyMovies(IMovie movie) {
        if (!getMyMovies().contains(movie)) {
            getMyMovies().add(movie);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeFromMyMovies(IMovie movie) {
        if (getMyMovies().contains(movie)) {
            getMyMovies().remove(movie);
            return true;
        }
        return false;
    }

    @Override
    public void addWatchedMovie(IMovie movie) {
        if (!getWatchedMovies().contains(movie)) {
            getWatchedMovies().add(movie);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + getID() +
                ", name='" + getName() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", age=" + getAge() +
                ", myMovies=" + getMyMovies() +
                ", watchedMovies=" + getWatchedMovies() +
                '}';
    }
}