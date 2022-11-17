package main;

import main.user.IUser;
import main.utils.ChillMediaFlow;
import main.utils.LogIn;
import main.utils.TextIO;
import main.utils.data.FileIO;
import main.utils.data.IDataIO;
import main.utils.data.SessionCache;

public class ChillMedia {

    private final IDataIO dataIO;
    private final ChillMediaFlow chillMediaFlow;
    private final SessionCache sessionCache;

    //private final ArrayList<IGenre> genres = new ArrayList<>(); // fjernes

    // TODO:
    //  - Fjerne LogIn stuff fra Konstruktøren og ned i run() metoden

    public ChillMedia() {
        this.sessionCache = new SessionCache();
        this.dataIO = new FileIO();
        load();
        LogIn logIn = new LogIn(this);
        logIn.logIn();
        IUser currentUser = logIn.getCurrentUser();
        getSessionCache().setUser(currentUser);
        this.chillMediaFlow = new ChillMediaFlow(this);
    }

    /*
     * This method gets the data from FileIO and puts it into the ArrayLists.
     *
     * @return Nothing.
     */
    private void load() {
        getSessionCache().setMovies(getDataIO().loadMovies());
        getSessionCache().setSeries(getDataIO().loadSeries());

        //this.getGenres().addAll(List.of(MovieGenre.values()));
        //this.getGenres().addAll(List.of(SeriesGenre.values()));
    }

    /*
     * This method starts the program.
     * The program asks the user to choose between the different options, and executes the methods behind the option.
     *
     * @return Nothing.
     */
    public void run() {
        TextIO.println("Welcome to ChillMedia!");
        TextIO.println("");
        String[] options = new String[] {
                "Log out",
                "Search for a movie",
                "Search for a series",
        };
        boolean run = true;
        while (run) {
            String input = TextIO.getUserInput("What would you like to do?", options);
            switch (input) {
                case "0" -> {
                    TextIO.println("Goodbye!");
                    run = false;
                }
                case "1" -> listMovies();
                case "2" -> listSeries();
                default -> TextIO.println("Invalid input!");
            }
        }
        getDataIO().saveUser(getSessionCache().getUser());
    }

    /*
     * This method prompts the user for options to search for a movie.
     *
     * @return Nothing.
     */
    private void listMovies() {
        String[] options = new String[] {
                "Exit",
                "Search all movies",
                "Search by title",
                "Search by genre",
                "Search by rating",
                "Search my movies",
                "Search my watched movies"
        };

        while (true) {
            String input = TextIO.getUserInput("Would you like to see?", options);
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
                TextIO.println("Invalid input!");
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
                "Search all series",
                "Search by title",
                "Search by genre",
                "Search by rating",
        };

        while (true) {
            String input = TextIO.getUserInput("Would you like to see?", options);
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
                TextIO.println("Invalid input!");
            }
        }
    }

    /*
    public ArrayList<IGenre> getGenres() {
        return this.genres;
    }
    */

    public IDataIO getDataIO() {
        return this.dataIO;
    }

    public SessionCache getSessionCache() {
        return sessionCache;
    }

    public ChillMediaFlow getChillMediaFlow() {
        return chillMediaFlow;
    }
}