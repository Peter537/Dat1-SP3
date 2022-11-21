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
     * This contructor creates a new user object
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


// this checks if the user is eligible to watch certain 18+ movies. It has not really been used yet.
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


    // adds movies the user set aside for later/saved
    // returns true if the selected movie was added successfully (which it should be if it doesnt already exist there)
    @Override
    public boolean addToMyMovies(IMovie movie) {
        if (!getMyMovies().contains(movie)) {
            getMyMovies().add(movie);
            return true;
        }
        return false;
    }


    // removes the movie, user previously saved/set aside for later
    //returns true if movie was removed successfully (checks if the saved movie was actually in the saved-movies list)
    @Override
    public boolean removeFromMyMovies(IMovie movie) {
        if (getMyMovies().contains(movie)) {
            getMyMovies().remove(movie);
            return true;
        }
        return false;
    }


    // adds any move the user has already watched to the watchedMovie list, if it has not already been seen.
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