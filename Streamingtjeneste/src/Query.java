import java.util.ArrayList;
import java.util.stream.Collectors;

public class Query {

    public static ArrayList<ISeries> searchSeriesGenre(ArrayList<ISeries> series, SeriesGenre genre) {
        return series.stream().filter(s -> s.getGenres().contains(genre)).collect(Collectors.toCollection(ArrayList::new));
    }

    public static ArrayList<IMovie> searchMovieGenre(ArrayList<IMovie> movies, MovieGenre genre) {
        return movies.stream().filter(m -> m.getGenres().contains(genre)).collect(Collectors.toCollection(ArrayList::new));
    }

    public static ArrayList<IMedia> searchGenre(ArrayList<IMedia> media, IGenre genre) {
        return media.stream().filter(m -> m.getGenres().contains(genre)).collect(Collectors.toCollection(ArrayList::new));
    }

    public static ArrayList<ISeries> searchSeriesName(ArrayList<ISeries> series, String name) {
        return series.stream().filter(s -> s.getName().equalsIgnoreCase(name)).collect(Collectors.toCollection(ArrayList::new));
    }

    public static ArrayList<IMovie> searchMovieName(ArrayList<IMovie> movies, String name) {
        return movies.stream().filter(m -> m.getName().equalsIgnoreCase(name)).collect(Collectors.toCollection(ArrayList::new));
    }

    public static ArrayList<IMedia> searchName(ArrayList<IMedia> media, String name) {
        return media.stream().filter(m -> m.getName().equalsIgnoreCase(name)).collect(Collectors.toCollection(ArrayList::new));
    }

    public static ArrayList<ISeries> searchSeriesRating(ArrayList<ISeries> series, float rating) {
        return series.stream().filter(s -> s.getRating() >= rating).collect(Collectors.toCollection(ArrayList::new));
    }

    public static ArrayList<IMovie> searchMovieRating(ArrayList<IMovie> movies, float rating) {
        return movies.stream().filter(m -> m.getRating() >= rating).collect(Collectors.toCollection(ArrayList::new));
    }

    public static ArrayList<IMedia> searchRating(ArrayList<IMedia> media, float rating) {
        return media.stream().filter(m -> m.getRating() >= rating).collect(Collectors.toCollection(ArrayList::new));
    }
}