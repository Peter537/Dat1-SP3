package main.user;

import java.util.ArrayList;
import main.media.IMovie;

public class User implements IUser {

    private static int idCounter = 0;
    private final int id;
    private final String name;
    private final String email;
    private final String password;
    private final int age;
    private final boolean isAdult;
    private final ArrayList<IMovie> myMovies;
    private final ArrayList<IMovie> watchedMovies;

    public User(int id, String name, String email, String password, int age, ArrayList<IMovie> myMovies, ArrayList<IMovie> watchedMovies) {
        if (id != -1) {
            this.id = id;
        } else {
            this.id = idCounter;
        }
        idCounter++;
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.myMovies = myMovies;
        this.watchedMovies = watchedMovies;
        this.isAdult = age >= 18;
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
        return this.isAdult;
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
    public boolean addMyMovie(IMovie movie) {
        if (!getMyMovies().contains(movie)) {
            getMyMovies().add(movie);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeMyMovie(IMovie movie) {
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
