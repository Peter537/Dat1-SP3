package main.utils;

import main.ChillMedia;
import main.genre.Genre;
import main.genre.IGenre;
import main.media.IMedia;
import main.media.IMovie;
import main.media.ISeries;
import main.user.IUser;

import java.util.ArrayList;
import java.util.Arrays;

public class ChillMediaFlow {

    private final ChillMedia chillMedia;
    private final IUser currentUser;

    /**
     * Constructor for ChillMediaFlow
     * <p>
     * KOMMENTAR_TIL_KONSTRUKTØREN_HER
     *
     * @param chillMedia
     */
    public ChillMediaFlow(ChillMedia chillMedia) {
        this.chillMedia = chillMedia;
        this.currentUser = chillMedia.getSessionCache().getUser();
    }

    /**
     *
     *
     * @param number
     */
    public void searchMovies(int number) {
        switch (number) {
            case 1 -> searchMovies(getChillMedia().getSessionCache().getMovies());
            case 2 -> searchMoviesByTitle();
            case 3 -> searchMoviesByGenre();
            case 4 -> searchMoviesByRating();
            case 5 -> searchMyMovies();
            case 6 -> searchMyWatchedMovies();
        }
    }

    /**
     *
     */
    private void searchMoviesByTitle() {
        ArrayList<IMovie> movies;
        while (true) {
            String title = TextIO.getUserInput("Enter a title to search for: ");
            movies = Query.searchMovieTitle(getChillMedia().getSessionCache().getMovies(), title);
            if (movies.size() > 0) {
                break;
            }
            TextIO.println("No movies found.");
        }
        searchMovies(movies);
    }

    /**
     *
     */
    private void searchMoviesByGenre() {
        ArrayList<IMovie> movies;
        while (true) {
            String genre = TextIO.getUserInput("Enter a genre to search for: ");
            if (Arrays.stream(Genre.values()).anyMatch(g -> g.name().equalsIgnoreCase(genre) && g.isMovieGenre())) {
                movies = Query.searchMovieGenre(getChillMedia().getSessionCache().getMovies(), genre);
                if (!getCurrentUser().isAdult()) {
                    movies = Query.searchMovieGenreAgeRestricted(movies, genre);
                }
                if (movies.size() > 0) {
                    break;
                }
                TextIO.println("No movies found.");
            } else {
                TextIO.println("The genre isn't there, you can choose from: ");
                for (IGenre g : Genre.values()) {
                    if (g.isMovieGenre()) {
                        if (g.isAgeRestricted() && !getCurrentUser().isAdult()) {
                            continue;
                        }
                        TextIO.println(" - " + g);
                    }
                }
            }
        }
        searchMovies(movies);
    }

    /**
     *
     */
    private void searchMoviesByRating() {
        ArrayList<IMovie> movies;
        while (true) {
            float minimumRating;
            try {
                String rating = TextIO.getUserInput("Enter a rating to search for: ");
                minimumRating = Float.parseFloat(rating);
            } catch (Exception e) {
                TextIO.println("Invalid rating.");
                return;
            }
            movies = Query.searchMovieRating(getChillMedia().getSessionCache().getMovies(), minimumRating);
            if (movies.size() > 0) {
                break;
            }
            TextIO.println("No movies found.");
        }
        searchMovies(movies);
    }

    /**
     *
     */
    private void searchMyMovies() {
        ArrayList<IMovie> movies = getCurrentUser().getMyMovies();
        if (movies.size() == 0) {
            TextIO.println("No movies found.");
            return;
        }
        searchMovies(movies);
    }

    /**
     *
     */
    private void searchMyWatchedMovies() {
        ArrayList<IMovie> movies = getCurrentUser().getWatchedMovies();
        if (movies.size() == 0) {
            TextIO.println("No movies found.");
            return;
        }
        searchMovies(movies);
    }

    /**
     *
     *
     * @param movies
     */
    private void searchMovies(ArrayList<IMovie> movies) {
        int page = 1;
        int pageSize = 10;
        int maxPage = Math.round((float) movies.size() / (float) pageSize);
        if (movies.size() % pageSize != 0) {
            maxPage++;
        }

        boolean b = true;
        while (b) {
            TextIO.println("Page " + page + " of " + maxPage);
            TextIO.println("Movies: " + movies.size());
            int choice = chooseMovie(movies, page, pageSize);
            if (choice < 0) {
                switch (choice) {
                    case -1 -> b = false;
                    case -2 -> {
                        page++;
                        if (page > maxPage) {
                            page = 1;
                        }
                    }
                    case -3 -> {
                        page--;
                        if (page < 1) {
                            page = maxPage;
                        }
                    }
                }
            } else {
                IMovie movie = movies.get(choice);
                if (movie == null) {
                    return;
                }

                int select = selectMovieOptions();
                switch (select) {
                    case 0 -> b = false;
                    case 1 -> {
                        watchMovie(movie);
                        b = false;
                    }
                    case 2 -> {
                        addMovieToMyMovies(movie);
                        b = false;
                    }
                    case 3 -> {
                        removeMovieFromMyMovies(movie);
                        b = false;
                    }
                    default -> TextIO.println("Invalid input!");
                }
            }
        }

        TextIO.getUserInput("Press enter to continue...");
    }


    /**
     *
     *
     * @param number
     */
    public void searchSeries(int number) {
        switch (number) {
            case 1 -> searchSeries(getChillMedia().getSessionCache().getSeries());
            case 2 -> searchSeriesByTitle();
            case 3 -> searchSeriesByGenre();
            case 4 -> searchSeriesByRating();
        }
    }

    /**
     *
     */
    private void searchSeriesByTitle() {
        ArrayList<ISeries> series;
        while (true) {
            String title = TextIO.getUserInput("Enter a title to search for: ");
            series = Query.searchSeriesTitle(getChillMedia().getSessionCache().getSeries(), title);
            if (series.size() > 0) {
                break;
            }
            TextIO.println("No series found.");
        }
        searchSeries(series);
    }

    /**
     *
     */
    private void searchSeriesByGenre() {
        ArrayList<ISeries> series;
        while (true) {
            String genre = TextIO.getUserInput("Enter a genre to search for: ");
            if (Arrays.stream(Genre.values()).anyMatch(g -> g.name().equalsIgnoreCase(genre) && g.isSeriesGenre())) {
                series = Query.searchSeriesGenre(getChillMedia().getSessionCache().getSeries(), genre);
                if (!getCurrentUser().isAdult()) {
                    series = Query.searchSeriesGenreAgeRestricted(series, genre);
                }
                if (series.size() > 0) {
                    break;
                }
                TextIO.println("No series found.");
            } else {
                TextIO.println("The genre isn't there, you can choose from: ");
                for (IGenre g : Genre.values()) {
                    if (g.isSeriesGenre()) {
                        if (g.isAgeRestricted() && !getCurrentUser().isAdult()) {
                            continue;
                        }
                        TextIO.println(" - " + g);
                    }
                }
            }
        }
        searchSeries(series);
    }

    /**
     *
     */
    private void searchSeriesByRating() {
        ArrayList<ISeries> series;
        while (true) {
            float minimumRating;
            try {
                String rating = TextIO.getUserInput("Enter a minimum rating to search for: ");
                minimumRating = Float.parseFloat(rating);
            } catch (Exception e) {
                TextIO.println("Invalid rating.");
                return;
            }
            series = Query.searchSeriesRating(getChillMedia().getSessionCache().getSeries(), minimumRating);
            if (series.size() > 0) {
                break;
            }
            TextIO.println("No series found.");
        }
        searchSeries(series);
    }

    /**
     *
     *
     * @param seriesList
     */
    private void searchSeries(ArrayList<ISeries> seriesList) {
        int page = 1;
        int pageSize = 10;
        int maxPage = Math.round((float) seriesList.size() / (float) pageSize);
        if (seriesList.size() % pageSize != 0) {
            maxPage++;
        }

        boolean b = true;
        while (b) {
            TextIO.println("Page " + page + " of " + maxPage);
            TextIO.println("Series: " + seriesList.size());
            int choice = chooseSeries(seriesList, page, pageSize);
            if (choice < 0) {
                switch (choice + 1) {
                    case 0 -> b = false;
                    case -1 -> {
                        page++;
                        if (page > maxPage) {
                            page = 1;
                        }
                    }
                    case -2 -> {
                        page--;
                        if (page < 1) {
                            page = maxPage;
                        }
                    }
                }
            } else {
                ISeries series = seriesList.get(choice);

                int select = selectSeriesOptions();
                switch (select) {
                    case 0 -> b = false;
                    case 1 -> {
                        watchSeries(series);
                        b = false;
                    }
                    default -> TextIO.println("Invalid input!");
                }
            }
        }

        TextIO.getUserInput("Press enter to continue...");
    }

    /**
     *
     *
     * @param series
     * @param page
     * @param pageSize
     * @return int
     */
    private int chooseSeries(ArrayList<ISeries> series, int page, int pageSize) {
        return chooseMedia(new ArrayList<>(series), page, pageSize);
    }

    /**
     *
     *
     * @param movies
     * @param page
     * @param pageSize
     * @return int
     */
    private int chooseMovie(ArrayList<IMovie> movies, int page, int pageSize) {
        return chooseMedia(new ArrayList<>(movies), page, pageSize);
    }

    /**
     *
     *
     * @param media
     * @param page
     * @param pageSize
     * @return int
     */
    private int chooseMedia(ArrayList<IMedia> media, int page, int pageSize) {
        while (true) {
            try {
                ArrayList<IMedia> shownMedia = new ArrayList<>();
                for (int i = (page - 1) * pageSize; i < page * pageSize; i++) {
                    if (i >= media.size()) {
                        break;
                    }
                    shownMedia.add(media.get(i));
                }
                String indexChoice = TextIO.getUserInputFromMedia("What do you want to see?", page, pageSize, shownMedia);
                int index = Integer.parseInt(indexChoice) - 1;
                if (index >= -3 && index < media.size()) {
                    return index;
                }
            } catch (Exception e) {
                TextIO.println("Invalid input. Please try again.");
            }
        }
    }

    /**
     *
     *
     * @param movie
     * @return Nothing.
     */
    private void addMovieToMyMovies(IMovie movie) {
        if (getCurrentUser().addToMyMovies(movie)) {
            TextIO.println("Added " + movie.getTitle() + " to your movies.");
        } else {
            TextIO.println("You already have " + movie.getTitle() + " in your movies.");
        }
    }

    /**
     *
     *
     * @param movie
     */
    private void removeMovieFromMyMovies(IMovie movie) {
        if (getCurrentUser().removeFromMyMovies(movie)) {
            TextIO.println("Removed " + movie.getTitle() + " from your movies.");
        } else {
            TextIO.println("You don't have " + movie.getTitle() + " in your movies.");
        }
    }

    /**
     *
     *
     * @param movie
     */
    private void watchMovie(IMovie movie) {
        getCurrentUser().addWatchedMovie(movie);
        TextIO.println("You are now watching " + movie.getTitle() + ".");
    }

    /**
     *
     *
     * @param series
     */
    private void watchSeries(ISeries series) {
        TextIO.println("You are now watching " + series.getTitle() + ".");
    }

    /**
     *
     *
     * @return int
     */
    private int selectMovieOptions() {
        String[] movieOptions = new String[]{
                "Exit",
                "Watch movie",
                "Add to my movies",
                "Remove from my movies",
        };

        while (true) {
            String input = TextIO.getUserInput("What would you like to do?", movieOptions);
            if (input.equals("0") || input.equals("1") || input.equals("2") || input.equals("3")) {
                return Integer.parseInt(input);
            }
            TextIO.println("Invalid input!");
        }
    }

    /**
     *
     *
     * @return int
     */
    private int selectSeriesOptions() {
        String[] seriesOptions = new String[]{
                "Exit",
                "Watch series",
        };

        while (true) {
            String input = TextIO.getUserInput("What would you like to do?", seriesOptions);
            if (input.equals("0") || input.equals("1")) {
                return Integer.parseInt(input);
            }
            TextIO.println("Invalid input!");
        }
    }

    private ChillMedia getChillMedia() {
        return chillMedia;
    }

    private IUser getCurrentUser() {
        return currentUser;
    }
}