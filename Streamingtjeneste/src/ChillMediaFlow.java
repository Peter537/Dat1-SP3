import media.IMedia;
import media.IMovie;
import media.ISeries;
import user.IUser;
import utils.Query;

import java.util.ArrayList;

public class ChillMediaFlow {

    // Lige nu er Next Page og Previous Page inde i select når man har valgt en film,
    // Skal have rettet så når man skal vælge film skal det se sådan her ud:
    // 0. Exit
    // 1-10 FILM
    // 11. Next Page
    // 12. Previous Page
    //
    // Og i select skal det være:
    // 0. Exit
    // "Watch movie" / "Watch series"
    // "Add to my movies" / NOTHING
    // "Remove from my movies" / NOTHING
    //
    // ****
    // SER UD TIL NOGEN LUNDE AT VIRKE...
    // SKAL LIGE HAVE SET OM DER KAN VÆRE 5 FILM PÅ EN SIDE HVIS NU DER ER 45 FILM SOM BLEV SØGT EFTER

    private final ChillMedia chillMedia;
    private final IUser currentUser;

    public ChillMediaFlow(ChillMedia chillMedia) {
        this.chillMedia = chillMedia;
        this.currentUser = chillMedia.getCurrentUser();
    }

    /*
    public void searchMoviesByTitle() {
        int page = 1;
        int pageSize = 10;
        while (true) {
            String title = chillMedia.getTextIO().getUserInput("Enter a title to search for: ");
            ArrayList<IMovie> movies = Query.searchMovieTitle(chillMedia.getMovies(), title);
            if (movies.size() == 0) {
                chillMedia.getTextIO().println("No movies found.");
                return;
            }

            IMovie movie = chooseMovie(movies);
            if (movie == null) {
                return;
            }

            page = selectAndShowMovieOptions(movies, movie, page, pageSize);
        }
    }

    public void searchMoviesByGenre() {
        int page = 1;
        int pageSize = 10;
        while (true) {
            String genre = chillMedia.getTextIO().getUserInput("Enter a genre to search for: ");
            ArrayList<IMovie> movies = Query.searchMovieGenre(chillMedia.getMovies(), genre);
            if (movies.size() == 0) {
                chillMedia.getTextIO().println("No movies found.");
                return;
            }

            IMovie movie = chooseMovie(movies);
            if (movie == null) {
                return;
            }

            page = selectAndShowMovieOptions(movies, movie, page, pageSize);
        }
    }

    public void searchMoviesByRating() {
        int page = 1;
        int pageSize = 10;
        while (true) {
            float minimumRating = -1;
            try {
                String rating = chillMedia.getTextIO().getUserInput("Enter a rating to search for: ");
                minimumRating = Float.parseFloat(rating);
            } catch (Exception e) {
                chillMedia.getTextIO().println("Invalid rating.");
                return;
            }
            ArrayList<IMovie> movies = Query.searchMovieRating(chillMedia.getMovies(), minimumRating);
            if (movies.size() == 0) {
                chillMedia.getTextIO().println("No movies found.");
                return;
            }

            IMovie movie = chooseMovie(movies);
            if (movie == null) {
                return;
            }

            page = selectAndShowMovieOptions(movies, movie, page, pageSize);
        }
    }

    public void searchMyMovies() {
        int page = 1;
        int pageSize = 10;
        while (true) {
            ArrayList<IMovie> movies = currentUser.getMyMovies();
            if (movies.size() == 0) {
                chillMedia.getTextIO().println("No movies found.");
                return;
            }

            IMovie movie = chooseMovie(movies);
            if (movie == null) {
                return;
            }

            page = selectAndShowMovieOptions(movies, movie, page, pageSize);
        }
    }

    public void searchMyWatchedMovies() {
        int page = 1;
        int pageSize = 10;
        while (true) {
            ArrayList<IMovie> movies = currentUser.getWatchedMovies();
            if (movies.size() == 0) {
                chillMedia.getTextIO().println("No movies found.");
                return;
            }

            IMovie movie = chooseMovie(movies);
            if (movie == null) {
                return;
            }

            page = selectAndShowMovieOptions(movies, movie, page, pageSize);
        }
    }
    */

    public void searchMoviesByTitle() {
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

    public void searchMoviesByGenre() {
        ArrayList<IMovie> movies;
        while (true) {
            String genre = chillMedia.getTextIO().getUserInput("Enter a genre to search for: ");
            movies = Query.searchMovieGenre(chillMedia.getMovies(), genre);
            if (movies.size() > 0) {
                break;
            }
            chillMedia.getTextIO().println("No movies found.");
        }
        searchMovies(movies);
    }

    public void searchMoviesByRating() {
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

    public void searchMyMovies() {
        ArrayList<IMovie> movies = currentUser.getMyMovies();
        if (movies.size() == 0) {
            chillMedia.getTextIO().println("No movies found.");
            return;
        }
        searchMovies(movies);
    }

    public void searchMyWatchedMovies() {
        ArrayList<IMovie> movies = currentUser.getWatchedMovies();
        if (movies.size() == 0) {
            chillMedia.getTextIO().println("No movies found.");
            return;
        }
        searchMovies(movies);
    }


    public void searchMovies(ArrayList<IMovie> movies) {
        int page = 1;
        int pageSize = 10;

        boolean b = true;
        while (b) {
            int choice = chooseMovie(movies, page, pageSize);
            if (choice < 0) {
                switch (choice) {
                    case -1 -> b = false;
                    case -2 -> {
                        page++;
                        if (page > movies.size() / pageSize) {
                            page = 1;
                        }
                    }
                    case -3 -> {
                        page--;
                        if (page < 1) {
                            page = movies.size() / pageSize;
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




    public void searchSeriesByTitle() {
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

    public void searchSeriesByGenre() {
        ArrayList<ISeries> series;
        while (true) {
            String genre = chillMedia.getTextIO().getUserInput("Enter a genre to search for: ");
            series = Query.searchSeriesGenre(chillMedia.getSeries(), genre);
            if (series.size() > 0) {
                break;
            }
            chillMedia.getTextIO().println("No series found.");
        }
        searchSeries(series);
    }

    public void searchSeriesByRating() {
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

    public void searchSeries(ArrayList<ISeries> seriesList) {
        int page = 1;
        int pageSize = 10;

        boolean b = true;
        while (b) {
            int choice = chooseSeries(seriesList, page, pageSize);
            if (choice < 0) {
                switch (choice + 1) {
                    case 0 -> b = false;
                    case -1 -> {
                        page++;
                        if (page > seriesList.size() / pageSize) {
                            page = 1;
                        }
                    }
                    case -2 -> {
                        page--;
                        if (page < 1) {
                            page = seriesList.size() / pageSize;
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
    public IMovie chooseMovie(ArrayList<IMovie> movies, int page) {
        ArrayList<IMedia> media = new ArrayList<>(movies);
        return (IMovie) chooseMedia(media, page);
    }
     */

    public int chooseSeries(ArrayList<ISeries> series, int page, int pageSize) {
        return chooseMedia(new ArrayList<>(series), page, pageSize);
    }

    public int chooseMovie(ArrayList<IMovie> movies, int page, int pageSize) {
        return chooseMedia(new ArrayList<>(movies), page, pageSize);
    }

    /*
    public ISeries chooseSeries(ArrayList<ISeries> series, int page) {
        ArrayList<IMedia> media = new ArrayList<>(series);
        return (ISeries) chooseMedia(media, page);
    }
     */

    public int chooseMedia(ArrayList<IMedia> media, int page, int pageSize) {
        while (true) {
            try {
                ArrayList<IMedia> shownMedia = new ArrayList<>();
                for (int i = (page - 1) * pageSize; i < page * pageSize; i++) {
                    if (i >= media.size()) {
                        break;
                    }
                    shownMedia.add(media.get(i));
                }
                String indexChoice = chillMedia.getTextIO().getUserInputFromMedia("What do you want to see?", page, shownMedia);
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
    public IMedia chooseMedia(ArrayList<IMedia> media, int page) {
        while (true) {
            try {
                ArrayList<IMedia> shownMedia = new ArrayList<>();
                for (int i = (page - 1) * 10; i < page * 10; i++) {
                    if (i >= media.size()) {
                        break;
                    }
                    shownMedia.add(media.get(i));
                }
                String indexChoice = chillMedia.getTextIO().getUserInputFromMedia("What do you want to see?", page, shownMedia);
                int index = Integer.parseInt(indexChoice) - 1;
                if (index >= 0 && index < media.size()) {
                    return media.get(index);
                } else if (index <= -1 && index >= -3) {
                    return null;
                }
            } catch (Exception e) {
                chillMedia.getTextIO().println("Invalid input. Please try again.");
            }
        }
    }
     */


    public void addMovieToMyMovies(IMovie movie) {
        if (currentUser.addMyMovie(movie)) {
            chillMedia.getTextIO().println("Added " + movie.getTitle() + " to your movies.");
        } else {
            chillMedia.getTextIO().println("You already have " + movie.getTitle() + " in your movies.");
        }
    }

    public void removeMovieFromMyMovies(IMovie movie) {
        if (currentUser.removeMyMovie(movie)) {
            chillMedia.getTextIO().println("Removed " + movie.getTitle() + " from your movies.");
        } else {
            chillMedia.getTextIO().println("You don't have " + movie.getTitle() + " in your movies.");
        }
    }

    public void watchMovie(IMovie movie) {
        currentUser.addWatchedMovie(movie);
        chillMedia.getTextIO().println("You are now watching " + movie.getTitle() + ".");
    }

    public void watchSeries(ISeries series) {
        chillMedia.getTextIO().println("You are now watching " + series.getTitle() + ".");
    }



    /*
    // Method name should be changed but IDK what to
    public int selectAndShowMovieOptions(ArrayList<IMovie> movies, IMovie movie, int page, int pageSize) {
        String[] movieOptions = new String[]{
                "Exit",
                "Watch movie",
                "Add to my movies",
                "Remove from my movies",
                "Next page",
                "Previous page"
        };

        while (true) {
            String input = chillMedia.getTextIO().getUserInput("What would you like to do?", movieOptions);
            switch (input) {
                case "0" -> {
                    return 0;
                }
                case "1" -> {
                    watchMovie(movie);
                    return page;
                }
                case "2" -> {
                    addMovie(movie);
                    return page;
                }
                case "3" -> {
                    removeMovie(movie);
                    return page;
                }
                case "4" -> {
                    page++;
                    if (page > movies.size() / pageSize) {
                        page = 1;
                    }
                    return page;
                }
                case "5" -> {
                    page--;
                    if (page < 1) {
                        page = movies.size() / pageSize;
                    }
                    return page;
                }
                default -> chillMedia.getTextIO().println("Invalid input!");
            }
        }
    }
     */

    public int selectMovieOptions() {
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

    public int selectSeriesOptions() {
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

    /*
    // Method name should be changed but IDK what to
    public int selectAndShowSeriesOptions(ArrayList<ISeries> seriesList, ISeries series, int page, int pageSize) {
        String[] seriesOptions = new String[] {
                "Exit",
                "Watch Series",
                "Next page",
                "Previous page"
        };

        while (true) {
            String input2 = chillMedia.getTextIO().getUserInput("What would you like to do?", seriesOptions);
            switch (input2) {
                case "0" -> {
                    return page;
                }
                case "1" -> {
                    watchSeries(series);
                    return page;
                }
                case "2" -> {
                    page++;
                    if (page > seriesList.size() / pageSize) {
                        page = 1;
                    }
                    return page;
                }
                case "3" -> {
                    page--;
                    if (page < 1) {
                        page = seriesList.size() / pageSize;
                    }
                    return page;
                }
                default -> chillMedia.getTextIO().println("Invalid input!");
            }
        }
    }
    */


    /*
     * SKAL SLETTES PÅ ET TIDSPUNKT
     * VAR INDE FRA ChillMedia, men så lavede jeg denne klasse,
     * så idk tbh hvad jeg vil gøre med det her, så jeg lader det bare stå her
     *
    private void listMoviesOLD() {
        String[] options = new String[] {
                "Exit",
                "Search by title",
                "Search by genre",
                "Search by rating",
                "Search my movies",
                "Search my watched movies"
        };
        while (true) {
            String input = textIO.getUserInput("How do you want to search?", options);
            switch (input) {
                case "0" -> {
                    run();
                    return;
                }
                case "1" -> {
                    int page = 1;
                    int pageSize = 10;
                    while (true) {
                        String title = textIO.getUserInput("What title do you want to search for?");
                        ArrayList<IMovie> movies = Query.searchMovieTitle(this.getMovies(), title);
                        if (movies.isEmpty()) {
                            textIO.println("No movies found!");
                            return;
                        }

                        String[] movieTitles = new String[10];
                        for (int i = (page - 1) * pageSize; i < page * pageSize; i++) {
                            movieTitles[i] = movies.get(i).getTitle();
                        }

                        if (movies.isEmpty()) {
                            textIO.println("No movies found!");
                            return;
                        }

                        IMovie movie;
                        try {
                            String indexString = textIO.getUserInput("Which movie do you want to see?", movieTitles);
                            int index = Integer.parseInt(indexString);
                            index += ((page - 1) * pageSize) - 1;
                            movie = movies.get(index);
                        } catch (Exception e) {
                            textIO.println("Invalid input!");
                            return;
                        }

                        String[] movieOptions = new String[] {
                                "Exit",
                                "Watch movie",
                                "Add to my movies",
                                "Remove from my movies",
                                "Next page",
                                "Previous page"
                        };

                        String input2 = textIO.getUserInput("What would you like to do?", movieOptions);
                        switch (input2) {
                            case "0" -> {
                                listMovies();
                                return;
                            }
                            case "1" -> {
                                textIO.println("You are now watching " + movie.getTitle());
                                currentUser.addWatchedMovie(movie);
                            }
                            case "2" -> {
                                currentUser.addMyMovie(movie);
                                textIO.println("Added " + movie.getTitle() + " to your movies!");
                            }
                            case "3" -> {
                                currentUser.removeMyMovie(movie);
                                textIO.println("Removed " + movie.getTitle() + " from your movies!");
                            }
                            case "4" -> {
                                page++;
                                if (page > movies.size() / pageSize) {
                                    page = 1;
                                }
                            }
                            case "5" -> {
                                page--;
                                if (page < 1) {
                                    page = movies.size() / pageSize;
                                }
                            }
                            default -> textIO.println("Invalid input!");
                        }
                    }
                }
                case "2" -> {
                    int page = 1;
                    int pageSize = 10;
                    while (true) {
                        String[] genreNames = new String[MovieGenre.values().length];
                        for (int i = 0; i < MovieGenre.values().length; i++) {
                            genreNames[i] = MovieGenre.values()[i].toString();
                        }
                        String genreName = textIO.getUserInput("What genre do you want to search for?", genreNames);
                        MovieGenre genre;
                        try {
                            genre = MovieGenre.valueOf(genreName);
                        } catch (Exception e) {
                            textIO.println("Invalid input!");
                            return;
                        }

                        ArrayList<IMovie> movies = Query.searchMovieGenre(this.getMovies(), genre);
                        if (movies.isEmpty()) {
                            textIO.println("No movies found!");
                            return;
                        }

                        String[] movieTitles = new String[10];
                        for (int i = (page - 1) * pageSize; i < page * pageSize; i++) {
                            movieTitles[i] = movies.get(i).getTitle();
                        }

                        IMovie movie;
                        try {
                            String indexString = textIO.getUserInput("Which movie do you want to see?", movieTitles);
                            int index = Integer.parseInt(indexString);
                            index += ((page - 1) * pageSize) - 1;
                            movie = movies.get(index);
                        } catch (Exception e) {
                            textIO.println("Invalid input!");
                            return;
                        }

                        String[] movieOptions = new String[]{
                                "Exit",
                                "Watch movie",
                                "Add to my movies",
                                "Remove from my movies",
                                "Next page",
                                "Previous page"
                        };

                        String input2 = textIO.getUserInput("What would you like to do?", movieOptions);
                        switch (input2) {
                            case "0" -> {
                                listMovies();
                                return;
                            }
                            case "1" -> {
                                textIO.println("You are now watching " + movie.getTitle());
                                currentUser.addWatchedMovie(movie);
                            }
                            case "2" -> {
                                currentUser.addMyMovie(movie);
                                textIO.println("Added " + movie.getTitle() + " to your movies!");
                            }
                            case "3" -> {
                                currentUser.removeMyMovie(movie);
                                textIO.println("Removed " + movie.getTitle() + " from your movies!");
                            }
                            case "4" -> {
                                page++;
                                if (page > movies.size() / pageSize) {
                                    page = 1;
                                }
                            }
                            case "5" -> {
                                page--;
                                if (page < 1) {
                                    page = movies.size() / pageSize;
                                }
                            }
                            default -> textIO.println("Invalid input!");
                        }
                    }
                }
                case "3" -> {
                    int page = 1;
                    int pageSize = 10;
                    while (true) {
                        String minRatingString = textIO.getUserInput("What is the minimum rating?");
                        float minRating;
                        try {
                            minRating = Float.parseFloat(minRatingString);
                        } catch (Exception e) {
                            textIO.println("Invalid input!");
                            return;
                        }

                        ArrayList<IMovie> movies = Query.searchMovieRating(this.getMovies(), minRating);
                        if (movies.isEmpty()) {
                            textIO.println("No movies found!");
                            return;
                        }

                        String[] movieTitles = new String[10];
                        for (int i = (page - 1) * pageSize; i < page * pageSize; i++) {
                            movieTitles[i] = movies.get(i).getTitle();
                        }

                        IMovie movie;
                        try {
                            String indexString = textIO.getUserInput("Which movie do you want to see?", movieTitles);
                            int index = Integer.parseInt(indexString);
                            index += ((page - 1) * pageSize) - 1;
                            movie = movies.get(index);
                        } catch (Exception e) {
                            textIO.println("Invalid input!");
                            return;
                        }

                        String[] movieOptions = new String[]{
                                "Exit",
                                "Watch movie",
                                "Add to my movies",
                                "Remove from my movies",
                                "Next page",
                                "Previous page"
                        };

                        String input2 = textIO.getUserInput("What would you like to do?", movieOptions);
                        switch (input2) {
                            case "0" -> {
                                listMovies();
                                return;
                            }
                            case "1" -> {
                                textIO.println("You are now watching " + movie.getTitle());
                                currentUser.addWatchedMovie(movie);
                            }
                            case "2" -> {
                                currentUser.addMyMovie(movie);
                                textIO.println("Added " + movie.getTitle() + " to your movies!");
                            }
                            case "3" -> {
                                currentUser.removeMyMovie(movie);
                                textIO.println("Removed " + movie.getTitle() + " from your movies!");
                            }
                            case "4" -> {
                                page++;
                                if (page > movies.size() / pageSize) {
                                    page = 1;
                                }
                            }
                            case "5" -> {
                                page--;
                                if (page < 1) {
                                    page = movies.size() / pageSize;
                                }
                            }
                            default -> textIO.println("Invalid input!");
                        }
                    }
                }
                case "4" -> {
                    int page = 1;
                    int pageSize = 10;
                    while (true) {
                        // my movies
                        ArrayList<IMovie> movies = currentUser.getMyMovies();
                        if (movies.isEmpty()) {
                            textIO.println("No movies found!");
                            return;
                        }

                        String[] movieTitles = new String[10];
                        for (int i = (page - 1) * pageSize; i < page * pageSize; i++) {
                            movieTitles[i] = movies.get(i).getTitle();
                        }

                        IMovie movie;
                        try {
                            String indexString = textIO.getUserInput("Which movie do you want to see?", movieTitles);
                            int index = Integer.parseInt(indexString);
                            index += ((page - 1) * pageSize) - 1;
                            movie = movies.get(index);
                        } catch (Exception e) {
                            textIO.println("Invalid input!");
                            return;
                        }

                        String[] movieOptions = new String[]{
                                "Exit",
                                "Watch movie",
                                "Remove from my movies",
                                "Next page",
                                "Previous page"
                        };

                        String input2 = textIO.getUserInput("What would you like to do?", movieOptions);
                        switch (input2) {
                            case "0" -> {
                                listMovies();
                                return;
                            }
                            case "1" -> {
                                textIO.println("You are now watching " + movie.getTitle());
                                currentUser.addWatchedMovie(movie);
                            }
                            case "2" -> {
                                currentUser.removeMyMovie(movie);
                                textIO.println("Removed " + movie.getTitle() + " from your movies!");
                            }
                            case "3" -> {
                                page++;
                                if (page > movies.size() / pageSize) {
                                    page = 1;
                                }
                            }
                            case "4" -> {
                                page--;
                                if (page < 1) {
                                    page = movies.size() / pageSize;
                                }
                            }
                            default -> textIO.println("Invalid input!");
                        }
                    }
                }
                case "5" -> {
                    int page = 1;
                    int pageSize = 10;
                    while (true) {
                        // watched movies
                        ArrayList<IMovie> movies = currentUser.getWatchedMovies();
                        if (movies.isEmpty()) {
                            textIO.println("No movies found!");
                            return;
                        }

                        String[] movieTitles = new String[10];
                        for (int i = (page - 1) * pageSize; i < page * pageSize; i++) {
                            movieTitles[i] = movies.get(i).getTitle();
                        }

                        IMovie movie;
                        try {
                            String indexString = textIO.getUserInput("Which movie do you want to see?", movieTitles);
                            int index = Integer.parseInt(indexString);
                            index += ((page - 1) * pageSize) - 1;
                            movie = movies.get(index);
                        } catch (Exception e) {
                            textIO.println("Invalid input!");
                            return;
                        }

                        String[] movieOptions = new String[]{
                                "Exit",
                                "Add to my movies",
                                "Remove from my movies",
                                "Next page",
                                "Previous page"
                        };

                        String input2 = textIO.getUserInput("What would you like to do?", movieOptions);
                        switch (input2) {
                            case "0" -> {
                                listMovies();
                                return;
                            }
                            case "1" -> {
                                currentUser.addMyMovie(movie);
                                textIO.println("Added " + movie.getTitle() + " to your movies!");
                            }
                            case "2" -> {
                                currentUser.removeMyMovie(movie);
                                textIO.println("Removed " + movie.getTitle() + " from your movies!");
                            }
                            case "3" -> {
                                page++;
                                if (page > movies.size() / pageSize) {
                                    page = 1;
                                }
                            }
                            case "4" -> {
                                page--;
                                if (page < 1) {
                                    page = movies.size() / pageSize;
                                }
                            }
                            default -> textIO.println("Invalid input!");
                        }
                    }
                }
                default -> textIO.println("Invalid input!");
            }
        }
    }
     */
}