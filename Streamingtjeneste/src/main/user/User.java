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
     * This constructor creates a new user object
     *
     * @param id represents the unique ID every user should be given
     * @param name represents the user's self-chosen name
     * @param email represents the email associated with user's account
     * @param password represents the user's password
     * @param age represents the user's age
     * @param myMovies represents the movies the user would like to set aside for later/save
     * @param watchedMovies represents the movies the user has already watched
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

    /**
     * @return boolean Value of whether the user is eligible to watch 18+ movies
     */
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

    /**
     * @param movie The movie to be added to the user's list of movies to watch later
     * @return boolean Value of whether the movie was added successfully
     */
    @Override
    public boolean addToMyMovies(IMovie movie) {
        if (!getMyMovies().contains(movie)) {
            getMyMovies().add(movie);
            return true;
        }
        return false;
    }

    /**
     * @param movie The movie to be removed from the user's list of movies to watch later
     * @return boolean Value of whether the movie was removed successfully
     */
    @Override
    public boolean removeFromMyMovies(IMovie movie) {
        if (getMyMovies().contains(movie)) {
            getMyMovies().remove(movie);
            return true;
        }
        return false;
    }

    /**
     * If the user has not already watched the movie, it is added to the user's list of watched movies,
     * otherwise it is not added
     *
     * @param movie The movie to be added to the user's list of movies already watched
     */
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