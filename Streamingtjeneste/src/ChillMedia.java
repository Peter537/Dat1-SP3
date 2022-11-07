import genre.IGenre;
import genre.MovieGenre;
import genre.SeriesGenre;
import media.IMovie;
import media.ISeries;
import user.IUser;
import user.User;
import utils.TextIO;
import utils.data.FileIO;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ChillMedia {

    private IUser currentUser;

    private final TextIO textIO;
    private final ChillMediaFlow chillMediaFlow;

    private final ArrayList<IGenre> genres = new ArrayList<>();
    private final ArrayList<IMovie> movies = new ArrayList<>();
    private final ArrayList<ISeries> series = new ArrayList<>();
    private final ArrayList<IUser> users = new ArrayList<>();
    private JList labelList;
    private JPanel panel1;

    public ChillMedia() {
        load();
        //LogIn logIn = new LogIn(this); // SKAL IKKE VÆRE UDKOMMENTERET
        //this.currentUser = logIn.getCurrentUser(); // SKAL IKKE VÆRE UDKOMMENTERET
        this.currentUser = new User(1, "Test", "test@gmail.com", "password", 19, new ArrayList<>(), new ArrayList<>()); // SKAL FJERNES
        this.textIO = new TextIO();
        this.chillMediaFlow = new ChillMediaFlow(this);
    }

    private void load() {
        FileIO fileIO = new FileIO();
        this.genres.addAll(List.of(MovieGenre.values()));
        this.genres.addAll(List.of(SeriesGenre.values()));
        this.movies.addAll(fileIO.loadMovies());
        this.series.addAll(fileIO.loadSeries());
        this.users.addAll(fileIO.loadUsers());
    }

    public void run() {
        textIO.println("Welcome to ChillMedia!");
        textIO.println("");
        String[] options = new String[] {
                "Exit",
                "Search for a movie",
                "Search for a series",
        };
        while (true) {
            String input = textIO.getUserInput("Would you like to see?", options);
            switch (input) {
                case "0" -> {
                    textIO.println("Goodbye!");
                    return;
                }
                case "1" -> listMovies();
                case "2" -> listSeries();
                default -> textIO.println("Invalid input!");
            }
        }


    }

    private void listMovies() {
        String[] options = new String[] {
                "Exit",
                "Search by title",
                "Search by genre",
                "Search by rating",
                "Search my movies",
                "Search my watched movies"
        };

        while (true) {
            String input = textIO.getUserInput("Would you like to see?", options);
            if (input.equals("0")) {
                return;
            }
            try {
                int number = Integer.parseInt(input);
                if (number < 1 || number > options.length) {
                    throw new NumberFormatException();
                }
                chillMediaFlow.searchMovies(number);
            } catch (NumberFormatException e) {
                textIO.println("Invalid input!");
            }
        }
    }

    private void listSeries() {
        String[] options = new String[] {
                "Exit",
                "Search by title",
                "Search by genre",
                "Search by rating",
        };

        while (true) {
            String input = textIO.getUserInput("Would you like to see?", options);
            if (input.equals("0")) {
                return;
            }
            try {
                int number = Integer.parseInt(input);
                if (number < 1 || number > options.length) {
                    throw new NumberFormatException();
                }
                chillMediaFlow.searchSeries(number);
            } catch (NumberFormatException e) {
                textIO.println("Invalid input!");
            }
        }
    }

    public ArrayList<IGenre> getGenres() {
        return genres;
    }

    public ArrayList<IMovie> getMovies() {
        return movies;
    }

    public ArrayList<ISeries> getSeries() {
        return series;
    }

    public ArrayList<IUser> getUsers() {
        return users;
    }

    public TextIO getTextIO() {
        return textIO;
    }

    public IUser getCurrentUser() {
        return currentUser;
    }
}