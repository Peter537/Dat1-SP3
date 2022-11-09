import genre.IGenre;
import genre.MovieGenre;
import genre.SeriesGenre;
import media.IMovie;
import media.ISeries;
import user.IUser;
import user.User;
import utils.TextIO;
import utils.UIForms.IUI;
import utils.UIForms.UI;
import utils.data.FileIO;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ChillMedia {

    private final IUser currentUser;

    private final TextIO textIO;
    private final FileIO fileIO;
    private final ChillMediaFlow chillMediaFlow;

    private final ArrayList<IGenre> genres = new ArrayList<>();
    private final ArrayList<IMovie> movies = new ArrayList<>();
    private final ArrayList<ISeries> series = new ArrayList<>();
    private final ArrayList<IUser> users = new ArrayList<>();
    public JFrame page;

    public ChillMedia() {
        this.fileIO = new FileIO();
        load();
        this.textIO = new TextIO();
        LogIn logIn = new LogIn(this); // SKAL IKKE VÆRE UDKOMMENTERET
        logIn.logIn();
        this.currentUser = logIn.getCurrentUser(); // SKAL IKKE VÆRE UDKOMMENTERET
        if (!users.contains(currentUser)) {
            users.add(currentUser);
        }
        this.chillMediaFlow = new ChillMediaFlow(this);
        page = new JFrame("Chill Media");
    }

    /*
     * This method gets the data from FileIO and puts it into the ArrayLists.
     *
     * @return Nothing.
     */
    private void load() {
        this.getGenres().addAll(List.of(MovieGenre.values()));
        this.getGenres().addAll(List.of(SeriesGenre.values()));
        this.getMovies().addAll(fileIO.loadMovies());
        this.getSeries().addAll(fileIO.loadSeries());
        this.getUsers().addAll(fileIO.loadUsers());
    }

    /*
     * This method starts the program.
     * The program asks the user to choose between the different options, and executes the methods behind the option.
     *
     * @return Nothing.
     */
    public void run() {
        getTextIO().println("Welcome to ChillMedia!");
        getTextIO().println("");
        String[] options = new String[] {
                "Exit",
                "Search for a movie",
                "Search for a series",
        };
        boolean run = true;
        while (run) {
            String input = getTextIO().getUserInput("Would you like to see?", options);
            switch (input) {
                case "0" -> {
                    getTextIO().println("Goodbye!");
                    run = false;
                }
                case "1" -> listMovies();
                case "2" -> listSeries();
                default -> getTextIO().println("Invalid input!");
            }
        }
        fileIO.save(users);
    }

    /*
     * This method prompts the user for options to search for a movie.
     *
     * @return Nothing.
     */
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
            String input = getTextIO().getUserInput("Would you like to see?", options);
            if (input.equals("0")) {
                return;
            }
            try {
                int number = Integer.parseInt(input);
                if (number < 1 || number > options.length) {
                    throw new NumberFormatException();
                }
                getChillMediaFlow().searchMovies(number);
            } catch (NumberFormatException e) {
                getTextIO().println("Invalid input!");
            }
        }
    }

    /*
     * This method prompts the user for options to search for a series.
     *
     * @return Nothing.
     */
    private void listSeries() {
        String[] options = new String[] {
                "Exit",
                "Search by title",
                "Search by genre",
                "Search by rating",
        };

        while (true) {
            String input = getTextIO().getUserInput("Would you like to see?", options);
            if (input.equals("0")) {
                return;
            }
            try {
                int number = Integer.parseInt(input);
                if (number < 1 || number > options.length) {
                    throw new NumberFormatException();
                }
                getChillMediaFlow().searchSeries(number);
            } catch (NumberFormatException e) {
                getTextIO().println("Invalid input!");
            }
        }
    }

    public ArrayList<IGenre> getGenres() {
        return this.genres;
    }

    public ArrayList<IMovie> getMovies() {
        return this.movies;
    }

    public ArrayList<ISeries> getSeries() {
        return this.series;
    }

    public ArrayList<IUser> getUsers() {
        return this.users;
    }

    public TextIO getTextIO() {
        return this.textIO;
    }

    public ChillMediaFlow getChillMediaFlow() {
        return chillMediaFlow;
    }

    public IUser getCurrentUser() {
        return this.currentUser;
    }
}