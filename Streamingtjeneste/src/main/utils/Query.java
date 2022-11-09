package main.utils;

import main.genre.IGenre;
import main.genre.MovieGenre;
import main.genre.SeriesGenre;
import main.media.IMedia;
import main.media.ISeries;
import main.media.IMovie;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Query {

    /*
     * This method returns a list of all series that matches the given genre.
     *
     * @param series The list of series to search through.
     * @param genre The genre to search for
     * @return ArrayList<ISeries> list of series that matches the given genre
     */
    public static ArrayList<ISeries> searchSeriesGenre(ArrayList<ISeries> series, SeriesGenre genre) {
        return series.stream().filter(s -> s.getGenres().contains(genre)).collect(Collectors.toCollection(ArrayList::new));
    }

    /*
     * This method returns a list of all series that matches the given genre.
     *
     * @param movies The list of series to search through.
     * @param genre The genre to search for
     * @return ArrayList<ISeries> list of series that matches the given genre
     */
    public static ArrayList<ISeries> searchSeriesGenre(ArrayList<ISeries> series, String genre) {
        return searchSeriesGenre(series, SeriesGenre.valueOf(genre.toUpperCase()));
    }

    /*
     * This method returns a list of all movies that matches the given genre.
     *
     * @param movies The list of movies to search through.
     * @param genre The genre to search for
     * @return ArrayList<IMovie> list of movies that matches the given genre
     */
    public static ArrayList<IMovie> searchMovieGenre(ArrayList<IMovie> movies, MovieGenre genre) {
        return movies.stream().filter(m -> m.getGenres().contains(genre)).collect(Collectors.toCollection(ArrayList::new));
    }

    /*
     * This method returns a list of all movies that matches the given genre.
     *
     * @param movies The list of movies to search through.
     * @param genre The genre to search for
     * @return ArrayList<IMovie> list of movies that matches the given genre
     */
    public static ArrayList<IMovie> searchMovieGenre(ArrayList<IMovie> movies, String genre) {
        return searchMovieGenre(movies, MovieGenre.valueOf(genre.toUpperCase()));
    }

    /*
     * This method isn't in use yet.
     */
    public static ArrayList<IMedia> searchGenre(ArrayList<IMedia> media, IGenre genre) {
        return media.stream().filter(m -> m.getGenres().contains(genre)).collect(Collectors.toCollection(ArrayList::new));
    }

    /*
     * This method returns a list of all series that matches the given title.
     *
     * @param series The list of series to search through.
     * @param title The title to search for
     * @return ArrayList<ISeries> list of series that matches the given title
     */
    public static ArrayList<ISeries> searchSeriesTitle(ArrayList<ISeries> series, String title) {
        return series.stream().filter(s -> s.getTitle().toLowerCase().contains(title.toLowerCase())).collect(Collectors.toCollection(ArrayList::new));
    }

    /*
     * This method returns a single title that matches the given title.
     *
     * @param series The list of series to search through.
     * @param title The title to search for
     * @return ISeries The series that matches the given title
     */
    public static ISeries searchSeriesTitleSingle(ArrayList<ISeries> series, String title) {
        return series.stream().filter(s -> s.getTitle().equals(title)).findFirst().orElse(null);
    }

    /*
     * This method returns a list of all movies that matches the given title.
     *
     * @param movies The list of movies to search through.
     * @param title The title to search for
     * @return ArrayList<IMovie> list of movies that matches the given title
     */
    public static ArrayList<IMovie> searchMovieTitle(ArrayList<IMovie> movies, String title) {
        return movies.stream().filter(m -> m.getTitle().toLowerCase().contains(title.toLowerCase())).collect(Collectors.toCollection(ArrayList::new));
    }

    /*
     * This method returns a single title that matches the given title.
     *
     * @param movies The list of movies to search through.
     * @param title The title to search for
     * @return IMovie The movie that matches the given title
     */
    public static IMovie searchMovieTitleSingle(ArrayList<IMovie> movies, String title) {
        return movies.stream().filter(m -> m.getTitle().equals(title)).findFirst().orElse(null);
    }

    /*
     * This method isn't in use yet.
     */
    public static ArrayList<IMedia> searchTitle(ArrayList<IMedia> media, String title) {
        return media.stream().filter(m -> m.getTitle().toLowerCase().contains(title.toLowerCase())).collect(Collectors.toCollection(ArrayList::new));
    }

    /*
     * This method returns a list of all series that matches the minimum rating.
     *
     * @param series The list of series to search through.
     * @param minimumRating The minimum rating to search for
     * @return ArrayList<ISeries> list of series that matches the given minimum rating
     */
    public static ArrayList<ISeries> searchSeriesRating(ArrayList<ISeries> series, float minimumRating) {
        return series.stream().filter(s -> s.getRating() >= minimumRating).collect(Collectors.toCollection(ArrayList::new));
    }

    /*
     * This method returns a list of all movies that matches the minimum rating.
     *
     * @param movies The list of movies to search through.
     * @param minimumRating The minimum rating to search for
     * @return ArrayList<IMovie> list of movies that matches the given minimum rating
     */
    public static ArrayList<IMovie> searchMovieRating(ArrayList<IMovie> movies, float minimumRating) {
        return movies.stream().filter(m -> m.getRating() >= minimumRating).collect(Collectors.toCollection(ArrayList::new));
    }

    /*
     * This method isn't in use yet.
     */
    public static ArrayList<IMedia> searchRating(ArrayList<IMedia> media, float minimumRating) {
        return media.stream().filter(m -> m.getRating() >= minimumRating).collect(Collectors.toCollection(ArrayList::new));
    }
}