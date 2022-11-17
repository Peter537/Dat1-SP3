package main.utils;

import main.genre.Genre;
import main.genre.IGenre;
import main.media.IMedia;
import main.media.ISeries;
import main.media.IMovie;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Query {

    /*
     *
     *
     * @param series
     * @param genre
     * @return ArrayList<ISeries>
     */
    public static ArrayList<ISeries> searchSeriesGenre(ArrayList<ISeries> series, IGenre genre) {
        return series.stream().filter(s -> s.getGenres().contains(genre) && genre.isSeriesGenre()).collect(Collectors.toCollection(ArrayList::new));
    }

    /*
     *
     *
     * @param series
     * @param genre
     * @return ArrayList<ISeries>
     */
    public static ArrayList<ISeries> searchSeriesGenre(ArrayList<ISeries> series, String genre) {
        return searchSeriesGenre(series, Genre.valueOf(genre.toUpperCase()));
    }

    /*
     *
     *
     * @param series
     * @param genre
     * @return ArrayList<ISeries>
     */
    public static ArrayList<ISeries> searchSeriesGenreAgeRestricted(ArrayList<ISeries> series, IGenre genre) {
        return series.stream().filter(s -> s.getGenres().contains(genre) && genre.isSeriesGenre() && !genre.isAgeRestricted()).collect(Collectors.toCollection(ArrayList::new));
    }

    /*
     *
     *
     * @param series
     * @param genre
     * @return ArrayList<ISeries>
     */
    public static ArrayList<ISeries> searchSeriesGenreAgeRestricted(ArrayList<ISeries> series, String genre) {
        return searchSeriesGenreAgeRestricted(series, Genre.valueOf(genre.toUpperCase()));
    }

    /*
     *
     *
     * @param movies
     * @param genre
     * @return ArrayList<IMovie>
     */
    public static ArrayList<IMovie> searchMovieGenre(ArrayList<IMovie> movies, IGenre genre) {
        return movies.stream().filter(m -> m.getGenres().contains(genre) && genre.isMovieGenre()).collect(Collectors.toCollection(ArrayList::new));
    }

    /*
     *
     *
     * @param movies
     * @param genre
     * @return ArrayList<IMovie>
     */
    public static ArrayList<IMovie> searchMovieGenre(ArrayList<IMovie> movies, String genre) {
        return searchMovieGenre(movies, Genre.valueOf(genre.toUpperCase()));
    }

    /*
     *
     *
     * @param movies
     * @param genre
     * @return ArrayList<IMovie>
     */
    public static ArrayList<IMovie> searchMovieGenreAgeRestricted(ArrayList<IMovie> movies, IGenre genre) {
        return movies.stream().filter(m -> m.getGenres().contains(genre) && genre.isMovieGenre() && !genre.isAgeRestricted()).collect(Collectors.toCollection(ArrayList::new));
    }

    /*
     *
     *
     * @param movies
     * @param genre
     * @return ArrayList<IMovie>
     */
    public static ArrayList<IMovie> searchMovieGenreAgeRestricted(ArrayList<IMovie> movies, String genre) {
        return searchMovieGenreAgeRestricted(movies, Genre.valueOf(genre.toUpperCase()));
    }

    /*
     *
     *
     * @param media
     * @param genre
     * @return ArrayList<IMedia>
     */
    public static ArrayList<IMedia> searchGenre(ArrayList<IMedia> media, IGenre genre) {
        return media.stream().filter(m -> m.getGenres().contains(genre)).collect(Collectors.toCollection(ArrayList::new));
    }

    /*
     *
     *
     * @param media
     * @param genre
     * @return ArrayList<IMedia>
     */
    public static ArrayList<IMedia> searchGenre(ArrayList<IMedia> media, String genre) {
        return searchGenre(media, Genre.valueOf(genre.toUpperCase()));
    }

    /*
     *
     *
     * @param media
     * @param genre
     * @return ArrayList<IMedia>
     */
    public static ArrayList<IMedia> searchGenreAgeRestricted(ArrayList<IMedia> media, IGenre genre) {
        return media.stream().filter(m -> m.getGenres().contains(genre) && !genre.isAgeRestricted()).collect(Collectors.toCollection(ArrayList::new));
    }

    /*
     *
     *
     * @param media
     * @param genre
     * @return ArrayList<IMedia>
     */
    public static ArrayList<IMedia> searchGenreAgeRestricted(ArrayList<IMedia> media, String genre) {
        return searchGenreAgeRestricted(media, Genre.valueOf(genre.toUpperCase()));
    }

    /*
     *
     *
     * @param series
     * @param title
     * @return ArrayList<ISeries>
     */
    public static ArrayList<ISeries> searchSeriesTitle(ArrayList<ISeries> series, String title) {
        return series.stream().filter(s -> s.getTitle().toLowerCase().contains(title.toLowerCase())).collect(Collectors.toCollection(ArrayList::new));
    }

    /*
     *
     *
     * @param series
     * @param title
     * @return ISeries
     */
    public static ISeries searchSeriesTitleSingle(ArrayList<ISeries> series, String title) {
        return series.stream().filter(s -> s.getTitle().equals(title)).findFirst().orElse(null);
    }

    /*
     *
     *
     * @param movies
     * @param title
     * @return ArrayList<IMovie>
     */
    public static ArrayList<IMovie> searchMovieTitle(ArrayList<IMovie> movies, String title) {
        return movies.stream().filter(m -> m.getTitle().toLowerCase().contains(title.toLowerCase())).collect(Collectors.toCollection(ArrayList::new));
    }

    /*
     *
     *
     * @param movies
     * @param title
     * @return IMovie
     */
    public static IMovie searchMovieTitleSingle(ArrayList<IMovie> movies, String title) {
        return movies.stream().filter(m -> m.getTitle().equals(title)).findFirst().orElse(null);
    }

    /*
     *
     *
     * @param media
     * @param title
     * @return ArrayList<IMedia>
     */
    public static ArrayList<IMedia> searchTitle(ArrayList<IMedia> media, String title) {
        return media.stream().filter(m -> m.getTitle().toLowerCase().contains(title.toLowerCase())).collect(Collectors.toCollection(ArrayList::new));
    }

    /*
     *
     *
     * @param series
     * @param minimumRating
     * @return ArrayList<ISeries>
     */
    public static ArrayList<ISeries> searchSeriesRating(ArrayList<ISeries> series, float minimumRating) {
        return series.stream().filter(s -> s.getRating() >= minimumRating).collect(Collectors.toCollection(ArrayList::new));
    }

    /*
     *
     *
     * @param movies
     * @param minimumRating
     * @return ArrayList<IMovie>
     */
    public static ArrayList<IMovie> searchMovieRating(ArrayList<IMovie> movies, float minimumRating) {
        return movies.stream().filter(m -> m.getRating() >= minimumRating).collect(Collectors.toCollection(ArrayList::new));
    }

    /*
     *
     *
     * @param media
     * @param minimumRating
     * @return ArrayList<IMedia>
     */
    public static ArrayList<IMedia> searchRating(ArrayList<IMedia> media, float minimumRating) {
        return media.stream().filter(m -> m.getRating() >= minimumRating).collect(Collectors.toCollection(ArrayList::new));
    }
}