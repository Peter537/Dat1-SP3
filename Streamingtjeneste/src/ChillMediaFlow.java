import genre.MovieGenre;
import genre.SeriesGenre;
import media.IMedia;
import media.IMovie;
import media.ISeries;
import user.IUser;
import utils.Query;
import utils.UIForms.IUI;
import utils.UIForms.UI;

import java.util.ArrayList;
import java.util.Arrays;

public class ChillMediaFlow {

    private final ChillMedia chillMedia;
    private final IUser currentUser;

    public ChillMediaFlow(ChillMedia chillMedia) {
        this.chillMedia = chillMedia;
        this.currentUser = chillMedia.getCurrentUser();

        IUI ui = new UI();
        ui.updatePane(ui, chillMedia.page);
    }

    /*
     * This method is used to determine what movies should be shown to the user
     *
     * @param number What to search for in the movies
     * @return Nothing
     */
    public void searchMovies(int number) {
        switch (number) {
            case 1 -> searchMoviesByTitle();
            case 2 -> searchMoviesByGenre();
            case 3 -> searchMoviesByRating();
            case 4 -> searchMyMovies();
            case 5 -> searchMyWatchedMovies();
        }
    }

    /*
     * This method shows movies by the title they enter in the method
     *
     * @return Nothing
     */
    private void searchMoviesByTitle() {
        ArrayList<IMovie> movies;
        while (true) {
            String title = chillMedia.getTextIO().getUserInput("Enter a title to search for: ");
            movies = Query.searchMovieTitle(chillMedia.getMovies(), title);
            if (movies.size() > 0) {
                break;
            }
            chillMedia.getTextIO().println("No movies found.");
        }
        searchMovies(movies);
    }

    /*
     * This method shows movies by the genre they enter
     *
     * @return Nothing
     */
    private void searchMoviesByGenre() {
        ArrayList<IMovie> movies;
        while (true) {
            String genre = chillMedia.getTextIO().getUserInput("Enter a genre to search for: ");
            if (Arrays.stream(MovieGenre.values()).anyMatch(movieGenre -> movieGenre.name().equalsIgnoreCase(genre))) {
                movies = Query.searchMovieGenre(chillMedia.getMovies(), genre);
                if (movies.size() > 0) {
                    break;
                }
                chillMedia.getTextIO().println("No movies found.");
            } else {
                chillMedia.getTextIO().println("The genre isn't there.");
            }
        }
        searchMovies(movies);
    }

    /*
     * This method shows movies by the minimum rating they enter
     *
     * @return Nothing
     */
    private void searchMoviesByRating() {
        ArrayList<IMovie> movies;
        while (true) {
            float minimumRating;
            try {
                String rating = chillMedia.getTextIO().getUserInput("Enter a rating to search for: ");
                minimumRating = Float.parseFloat(rating);
            } catch (Exception e) {
                chillMedia.getTextIO().println("Invalid rating.");
                return;
            }
            movies = Query.searchMovieRating(chillMedia.getMovies(), minimumRating);
            if (movies.size() > 0) {
                break;
            }
            chillMedia.getTextIO().println("No movies found.");
        }
        searchMovies(movies);
    }

    /*
     * This method shows movies the user has added to their list
     *
     * @return Nothing
     */
    private void searchMyMovies() {
        ArrayList<IMovie> movies = currentUser.getMyMovies();
        if (movies.size() == 0) {
            chillMedia.getTextIO().println("No movies found.");
            return;
        }
        searchMovies(movies);
    }

    /*
     * This method shows the movies the user has watched
     *
     * @return Nothing
     */
    private void searchMyWatchedMovies() {
        ArrayList<IMovie> movies = currentUser.getWatchedMovies();
        if (movies.size() == 0) {
            chillMedia.getTextIO().println("No movies found.");
            return;
        }
        searchMovies(movies);
    }

    /*
     * This method shows the movies the user has added to the list to search through
     *
     * @param movies The list of movies to show
     * @return Nothing
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
            chillMedia.getTextIO().println("Page " + page + " of " + maxPage);
            chillMedia.getTextIO().println("Movies: " + movies.size());
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
                    default -> chillMedia.getTextIO().println("Invalid input!");
                }
            }
        }

        chillMedia.getTextIO().getUserInput("Press enter to continue...");
    }


    /*
     * This method is used to determine what series should be shown to the user
     *
     * @param number What to search for in the series
     * @return Nothing
     */
    public void searchSeries(int i) {
        switch (i) {
            case 1 -> searchSeriesByTitle();
            case 2 -> searchSeriesByGenre();
            case 3 -> searchSeriesByRating();
        }
    }

    /*
     * This method shows series by the title they enter
     *
     * @return Nothing
     */
    private void searchSeriesByTitle() {
        ArrayList<ISeries> series;
        while (true) {
            String title = chillMedia.getTextIO().getUserInput("Enter a title to search for: ");
            series = Query.searchSeriesTitle(chillMedia.getSeries(), title);
            if (series.size() > 0) {
                break;
            }
            chillMedia.getTextIO().println("No series found.");
        }
        searchSeries(series);
    }

    /*
     * This method shows series by the genre they enter
     *
     * @return Nothing
     */
    private void searchSeriesByGenre() {
        ArrayList<ISeries> series;
        while (true) {
            String genre = chillMedia.getTextIO().getUserInput("Enter a genre to search for: ");
            if (Arrays.stream(SeriesGenre.values()).anyMatch(seriesGenre -> seriesGenre.name().equalsIgnoreCase(genre))) {
                series = Query.searchSeriesGenre(chillMedia.getSeries(), genre);
                if (series.size() > 0) {
                    break;
                }
                chillMedia.getTextIO().println("No series found.");
            } else {
                chillMedia.getTextIO().println("The genre isn't there.");
            }
        }
        searchSeries(series);
    }

    /*
     * This method shows series by the minimum rating they enter
     *
     * @return Nothing
     */
    private void searchSeriesByRating() {
        ArrayList<ISeries> series;
        while (true) {
            float minimumRating;
            try {
                String rating = chillMedia.getTextIO().getUserInput("Enter a minimum rating to search for: ");
                minimumRating = Float.parseFloat(rating);
            } catch (Exception e) {
                chillMedia.getTextIO().println("Invalid rating.");
                return;
            }
            series = Query.searchSeriesRating(chillMedia.getSeries(), minimumRating);
            if (series.size() > 0) {
                break;
            }
            chillMedia.getTextIO().println("No series found.");
        }
        searchSeries(series);
    }

    /*
     * This method shows the series the user has added to the list to search through
     *
     * @param seriesList The list of series to show
     * @return Nothing
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
            chillMedia.getTextIO().println("Page " + page + " of " + maxPage);
            chillMedia.getTextIO().println("Series: " + seriesList.size());
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
                    default -> chillMedia.getTextIO().println("Invalid input!");
                }
            }
        }

        chillMedia.getTextIO().getUserInput("Press enter to continue...");
    }

    /*
     * This method converts the series to media and the calls the chooseMedia method
     *
     * @param series The series to convert
     * @param page The page to show
     * @param pageSize The amount of movies to show per page
     * @return The index of the movie the user selected
     */
    private int chooseSeries(ArrayList<ISeries> series, int page, int pageSize) {
        return chooseMedia(new ArrayList<>(series), page, pageSize);
    }

    /*
     * This method converts the movies to media and then calls the chooseMedia method
     *
     * @param movies The movies to convert
     * @param page The page to show
     * @param pageSize The amount of movies to show per page
     * @return The index of the movie the user selected
     */
    private int chooseMovie(ArrayList<IMovie> movies, int page, int pageSize) {
        return chooseMedia(new ArrayList<>(movies), page, pageSize);
    }

    /*
     * This method is used to choose a media from a list
     *
     * @param mediaList The list of media to choose from
     * @param page The page to show
     * @param pageSize The amount of media to show per page
     * @return The index of the media chosen
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
                String indexChoice = chillMedia.getTextIO().getUserInputFromMedia("What do you want to see?", page, pageSize, shownMedia);
                int index = Integer.parseInt(indexChoice) - 1;
                if (index >= -3 && index < media.size()) {
                    return index;
                }
            } catch (Exception e) {
                chillMedia.getTextIO().println("Invalid input. Please try again.");
            }
        }
    }

    /*
     * This method adds a movie to the users movie list
     * If the movie isn't on the list already, it will be added and the user will be notified
     * If the movie is already on the list, the user will be notified
     *
     * @param movie The movie to add
     * @return Nothing
     */
    private void addMovieToMyMovies(IMovie movie) {
        if (currentUser.addMyMovie(movie)) {
            chillMedia.getTextIO().println("Added " + movie.getTitle() + " to your movies.");
        } else {
            chillMedia.getTextIO().println("You already have " + movie.getTitle() + " in your movies.");
        }
    }

    /*
     * This method removes a movie from the users movie list
     * If the movie is on the list, it will be removed and the user will be notified
     * If the movie isn't on the list, the user will be notified
     *
     * @param movie The movie to remove
     * @return Nothing
     */
    private void removeMovieFromMyMovies(IMovie movie) {
        if (currentUser.removeMyMovie(movie)) {
            chillMedia.getTextIO().println("Removed " + movie.getTitle() + " from your movies.");
        } else {
            chillMedia.getTextIO().println("You don't have " + movie.getTitle() + " in your movies.");
        }
    }

    /*
     * This method watches a movie for the user and adds it to their watched movies list
     *
     * @param movie The movie to watch
     * @return Nothing
     */
    private void watchMovie(IMovie movie) {
        currentUser.addWatchedMovie(movie);
        chillMedia.getTextIO().println("You are now watching " + movie.getTitle() + ".");
    }

    /*
     * This method adds a series to the users series list
     *
     * @param series The series to watch
     * @return Nothing
     */
    private void watchSeries(ISeries series) {
        chillMedia.getTextIO().println("You are now watching " + series.getTitle() + ".");
    }

    /*
     * This method shows the options a user has to make when they have selected a movie
     *
     * @return The option the user selected
     */
    private int selectMovieOptions() {
        String[] movieOptions = new String[]{
                "Exit",
                "Watch movie",
                "Add to my movies",
                "Remove from my movies",
        };

        while (true) {
            String input = chillMedia.getTextIO().getUserInput("What would you like to do?", movieOptions);
            if (input.equals("0") || input.equals("1") || input.equals("2") || input.equals("3")) {
                return Integer.parseInt(input);
            }
            chillMedia.getTextIO().println("Invalid input!");
        }
    }

    /*
     * This method shows the options a user has to make when they have selected a series
     *
     * @return The option the user selected
     */
    private int selectSeriesOptions() {
        String[] seriesOptions = new String[]{
                "Exit",
                "Watch series",
        };

        while (true) {
            String input = chillMedia.getTextIO().getUserInput("What would you like to do?", seriesOptions);
            if (input.equals("0") || input.equals("1")) {
                return Integer.parseInt(input);
            }
            chillMedia.getTextIO().println("Invalid input!");
        }
    }
}