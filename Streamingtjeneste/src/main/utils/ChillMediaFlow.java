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

    /**
     * Constructor for ChillMediaFlow
     * <p>
     * This contructor takes an instance of ChillMedia upon startup and lets the user prompt through the flow of the program
     * Here they can directly interact with the program
     *
     * @param chillMedia is an instance of ChillMedia
     */
    public ChillMediaFlow(ChillMedia chillMedia) {
        this.chillMedia = chillMedia;
    }

    /**
     * This method lets the user search movies by different parameters (e.g. title, genre, rating as listed below)
     *
     * @param number is the number the user has chosen to prompt into the program
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
     * This method lets the user search movies by title
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
     * This method lets the user search movies by a specific genre
     */
    private void searchMoviesByGenre() {
        ArrayList<IMovie> movies;
        while (true) {
            String genre = TextIO.getUserInput("Enter a genre to search for: ");
            if (Arrays.stream(Genre.values()).anyMatch(g -> g.name().equalsIgnoreCase(genre) && g.isMovieGenre())) {
                movies = Query.searchMovieGenre(getChillMedia().getSessionCache().getMovies(), genre);
                if (!getUser().isAdult()) {
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
                        if (g.isAgeRestricted() && !getUser().isAdult()) {
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
     * This method lets the user search movies by minimum rating
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
     * This method shows the user a list of their saved movies, or tells them the list is empty
     */
    private void searchMyMovies() {
        ArrayList<IMovie> movies = getUser().getMyMovies();
        if (movies.size() == 0) {
            TextIO.println("No movies found.");
            return;
        }
        searchMovies(movies);
    }

    /**
     * Shows user a list of movies they have already watched
     */
    private void searchMyWatchedMovies() {
        ArrayList<IMovie> movies = getUser().getWatchedMovies();
        if (movies.size() == 0) {
            TextIO.println("No movies found.");
            return;
        }
        searchMovies(movies);
    }

    /**
     * Determines how the page of displayed movies are shown to the user. Here we made it so that only 10 movies a shown per page
     * This method also lets the user browse through a certain amount of pages depending on their search
     *
     * @param movies is the arraylist of movies loaded by one of the other methods based on how the user searched for movies
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
     * This method gives the user multiple options of searching for a series (e.g. by title, genre or rating as listed below)
     *
     * @param number is the number the user prompted into the program.
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
     * Lets the user search series by title
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
     * Lets the user search series by a specific genre
     */
    private void searchSeriesByGenre() {
        ArrayList<ISeries> series;
        while (true) {
            String genre = TextIO.getUserInput("Enter a genre to search for: ");
            if (Arrays.stream(Genre.values()).anyMatch(g -> g.name().equalsIgnoreCase(genre) && g.isSeriesGenre())) {
                series = Query.searchSeriesGenre(getChillMedia().getSessionCache().getSeries(), genre);
                if (!getUser().isAdult()) {
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
                        if (g.isAgeRestricted() && !getUser().isAdult()) {
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
     * Lets the user search series by minimum rating
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
     * This method determines how the list of searched movies is displayed.
     * currently this method is set to show 10 movies per page and lets user browse through a certain amount of
     * series based on their query
     *
     * @param seriesList is the list of series loaded in by one of the above methods. They return a list of series that match the query
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
     * This method converts ISeries list of movies to IMedia
     *
     * @param series is an arraylist of series
     * @param page is the page number
     * @param pageSize is the page size (here we have chosen 10 movies per page is shown)
     * @return int returns the index number of displayed series (they are displayed as numbers and titles)
     */
    private int chooseSeries(ArrayList<ISeries> series, int page, int pageSize) {
        return chooseMedia(new ArrayList<>(series), page, pageSize);
    }

    /**
     * This method converts IMovie list of movies to IMedia
     *
     * @param movies is an arraylist of movies
     * @param page is the page number
     * @param pageSize is the page size (here we have chosen it should be 10 displayed movies per page)
     * @return int returns the index number of displayed movies which the user chose by prompting a number (they are displayed as numbers and titles)
     */
    private int chooseMovie(ArrayList<IMovie> movies, int page, int pageSize) {
        return chooseMedia(new ArrayList<>(movies), page, pageSize);
    }

    /**
     * This method is what displays each media for the user to choose from based on the user's query
     *
     * @param media is an arraylist of type IMedia that has a list of media objects
     * @param page is the page number
     * @param pageSize is the page size (here we have chosen it should be 10 displayed media per page)
     * @return int returns the index number of displayed media which the user chose by prompting a number (they are displayed as numbers and titles)
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
     * This method calls the methods created in User that adds an instance of a movie that was not already saved to myMovies
     *
     * @param movie is the selected movie instance to add to this list
     */
    private void addMovieToMyMovies(IMovie movie) {
        if (getUser().addToMyMovies(movie)) {
            TextIO.println("Added " + movie.getTitle() + " to your movies.");
        } else {
            TextIO.println("You already have " + movie.getTitle() + " in your movies.");
        }
    }

    /**
     * This method calls the methods created in User that removes an instance of a movie that was previously saved to myMovies
     *
     * @param movie is the selected movie instance to remove from this list
     */
    private void removeMovieFromMyMovies(IMovie movie) {
        if (getUser().removeFromMyMovies(movie)) {
            TextIO.println("Removed " + movie.getTitle() + " from your movies.");
        } else {
            TextIO.println("You don't have " + movie.getTitle() + " in your movies.");
        }
    }

    /**
     * This method is what displays what the user has chosen to watch.
     * This is the same as the user watching the media
     *
     * @param movie is the movie instance the user has chosen to watch
     */
    private void watchMovie(IMovie movie) {
        getUser().addWatchedMovie(movie);
        TextIO.println("You are now watching " + movie.getTitle() + ".");
    }

    /**
     * This method is what displays what the user has chosen to watch.
     * This is the same as the user watching the media
     *
     * @param series is the series instance the user has chosen to watch
     */
    private void watchSeries(ISeries series) {
        TextIO.println("You are now watching " + series.getTitle() + ".");
    }

    /**
     * prompts the user what they want to do with the selected movie
     *
     * @return int returns the integer choice based on the numerical listed options they were shown below.
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
     * This method prompts the user what they want to do with the selected series
     *
     * @return int returns the integer choice based on numerical listed options they were shown below.
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

    private IUser getUser() {
        return getChillMedia().getSessionCache().getUser();
    }
}