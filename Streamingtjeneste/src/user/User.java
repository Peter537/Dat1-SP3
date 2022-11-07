package user;

import user.IUser;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import media.IMovie;

public class User implements IUser {
    private static int idCounter = 0;
    private int id;
    private String name;
    private String password;
    private String email;
    private int age;
    private ArrayList<IMovie> myMovies;
    private ArrayList<IMovie> watchedMovies;

    public User(int ID, String name, String password, String email, int age, ArrayList<IMovie> myMovies, ArrayList<IMovie> watchedMovies) {
        if (ID != -1)
            this.id = ID;
        idCounter++;
        this.name = name;
        this.password = password;
        this.email = email;
        this.age = age;
        this.myMovies = myMovies;
        this.watchedMovies = watchedMovies;
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

    public void save(ArrayList<IUser> users) {

        try {
            FileWriter writer = new FileWriter("Data/user.csv");

            for (IUser user : users) {
                String myMovies = user.getMyMovies().toString().replaceAll("\\[", "").replaceAll("\\]", "");
                String watchedMovies = user.getWatchedMovies().toString().replaceAll("\\[", "").replaceAll("\\]", "");
                writer.write(user.getId() + "," + user.getName() + "," + user.getEmail() + "," + user.getPassword() + "," + user.getAge() + "," + myMovies + "," + watchedMovies +"\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
