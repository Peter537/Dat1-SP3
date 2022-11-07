package user;

import java.util.ArrayList;
import media.IMovie;

public class User implements IUser {

    private static int idCounter = 0;
    private final int id;
    private final String name;
    private final String password;
    private final String email;
    private final int age;
    private final boolean isAdult;
    private final ArrayList<IMovie> myMovies;
    private final ArrayList<IMovie> watchedMovies;

    public User(int id, String name, String email, String password, int age, ArrayList<IMovie> myMovies, ArrayList<IMovie> watchedMovies) {
        if (id != -1) {
            this.id = id;
        } else {
            this.id = idCounter++;
        }
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.myMovies = myMovies;
        this.watchedMovies = watchedMovies;
        this.isAdult = age >= 18;
    }

    public int getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public int getAge() {
        return this.age;
    }

    public boolean isAdult() {
        return this.isAdult;
    }

    public ArrayList<IMovie> getMyMovies() {
        return this.myMovies;
    }

    public ArrayList<IMovie> getWatchedMovies() {
        return this.watchedMovies;
    }

    public boolean addMyMovie(IMovie movie) {
        if (!getMyMovies().contains(movie)) {
            getMyMovies().add(movie);
            return true;
        }
        return false;
    }

    public boolean removeMyMovie(IMovie movie) {
        if (getMyMovies().contains(movie)) {
            getMyMovies().remove(movie);
            return true;
        }
        return false;
    }

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
