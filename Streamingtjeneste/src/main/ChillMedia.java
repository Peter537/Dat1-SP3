package main;

import main.user.IUser;
import main.utils.ChillMediaFlow;
import main.utils.LogIn;
import main.utils.TextIO;
import main.utils.data.IDataIO;
import main.utils.data.SessionCache;

public class ChillMedia {

    private final IDataIO dataIO;
    private final ChillMediaFlow chillMediaFlow;
    private final SessionCache sessionCache;
    private final LogIn logIn;

    /**
     * Constructor for ChillMedia
     * <p>
     * KOMMENTAR_TIL_KONSTRUKTØREN_HER
     *
     * @param dataIO
     */
    public ChillMedia(IDataIO dataIO) {
        this.sessionCache = new SessionCache();
        this.dataIO = dataIO;
        this.logIn = new LogIn(this);
        load();
        this.chillMediaFlow = new ChillMediaFlow(this);
    }

    /**
     *
     */
    private void load() {
        getSessionCache().setMovies(getDataIO().loadMovies());
        getSessionCache().setSeries(getDataIO().loadSeries());
    }

    /**
     *
     */
    public void run() {
        getLogIn().logIn();
        IUser user = getLogIn().getUser();
        getSessionCache().setUser(user);

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

    /**
     *
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

    /**
     *
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

    public IDataIO getDataIO() {
        return this.dataIO;
    }

    public SessionCache getSessionCache() {
        return sessionCache;
    }

    public ChillMediaFlow getChillMediaFlow() {
        return chillMediaFlow;
    }

    private LogIn getLogIn() {
        return logIn;
    }
}