package user;

import user.IUser;

import java.io.FileWriter;
import java.io.IOException;
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

    public boolean isAdult() {
        return isAdult;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", myMovies=" + myMovies +
                ", watchedMovies=" + watchedMovies +
                '}';
    }
}
