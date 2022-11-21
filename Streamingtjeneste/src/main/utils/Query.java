package main.utils;

import main.genre.Genre;
import main.genre.IGenre;
import main.media.IMedia;
import main.media.ISeries;
import main.media.IMovie;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Query {

    /**
     * This method searches for genres within series using IGenre type
     *
     * @param series list of queried series
     * @param genre IGenre type genre
     * @return ArrayList<ISeries> returns all series that is of said genre
     */
    public static ArrayList<ISeries> searchSeriesGenre(ArrayList<ISeries> series, IGenre genre) {
        return series.stream().filter(s -> s.getGenres().contains(genre) && genre.isSeriesGenre()).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * This method is an overload that queries using genre string rather than an IGenre reference
     *
     * @param series is an arraylist of series
     * @param genre is the inputted genre string
     * @return ArrayList<ISeries> returns all series that contains inputted genre
     */
    public static ArrayList<ISeries> searchSeriesGenre(ArrayList<ISeries> series, String genre) {
        return searchSeriesGenre(series, Genre.valueOf(genre.toUpperCase()));
    }

    /**
     * This method searches through all series that contains inputted genre and that are not age resitrcted
     *
     * @param series is a list of all series
     * @param genre is the inputted genre
     * @return ArrayList<ISeries> returns a list of series that match the genre and are not age restricted
     */
    public static ArrayList<ISeries> searchSeriesGenreAgeRestricted(ArrayList<ISeries> series, IGenre genre) {
        return series.stream().filter(s -> s.getGenres().contains(genre) && genre.isSeriesGenre() && !genre.isAgeRestricted()).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * search series is another overloaded method that uses string instead of IGenre type
     *
     * @param series is a list of series
     * @param genre is the inputted genre
     * @return ArrayList<ISeries> returns an arraylist of series that match the genre and are not age restricted
     */
    public static ArrayList<ISeries> searchSeriesGenreAgeRestricted(ArrayList<ISeries> series, String genre) {
        return searchSeriesGenreAgeRestricted(series, Genre.valueOf(genre.toUpperCase()));
    }

    /**
     * This method searches through movies that match the genre inputted
     *
     * @param movies is a list of all movies
     * @param genre is the genre inputted
     * @return ArrayList<IMovie> returns all movies that match the genre inputted
     */
    public static ArrayList<IMovie> searchMovieGenre(ArrayList<IMovie> movies, IGenre genre) {
        return movies.stream().filter(m -> m.getGenres().contains(genre) && genre.isMovieGenre()).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     *
     *
     * @param movies This method is an overload of the previous method and uses string genre rather than IGenre type
     * @param genre is the genre string inputted
     * @return ArrayList<IMovie> returns all movies that match the genre string input
     */
    public static ArrayList<IMovie> searchMovieGenre(ArrayList<IMovie> movies, String genre) {
        return searchMovieGenre(movies, Genre.valueOf(genre.toUpperCase()));
    }

    /**
     * searched through movies that match the genre and that are not age restricted
     *
     * @param movies is a list of all movies
     * @param genre is the genre of type IGenre that is inputted
     * @return ArrayList<IMovie> returns all movies that match the genre inputted
     */
    public static ArrayList<IMovie> searchMovieGenreAgeRestricted(ArrayList<IMovie> movies, IGenre genre) {
        return movies.stream().filter(m -> m.getGenres().contains(genre) && genre.isMovieGenre() && !genre.isAgeRestricted()).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * This method searches through all movies and returns the movies that match the genre and that are not age restricted
     * This method is an overload of the previous method
     *
     * @param movies is a list of all movies
     * @param genre is the genre input as string
     * @return ArrayList<IMovie> returns all movies that match the genre inputted
     */
    public static ArrayList<IMovie> searchMovieGenreAgeRestricted(ArrayList<IMovie> movies, String genre) {
        return searchMovieGenreAgeRestricted(movies, Genre.valueOf(genre.toUpperCase()));
    }

    /**
     * This method queries both series and media and streams all genre
     *
     * @param media is a list of all media
     * @param genre is a genre og type IGenre
     * @return ArrayList<IMedia> returns an arraylist of all media that matches the genre
     */
    public static ArrayList<IMedia> searchGenre(ArrayList<IMedia> media, IGenre genre) {
        return media.stream().filter(m -> m.getGenres().contains(genre)).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * This method is an overload and takes in a string genre rather than IGenre type genre
     *
     * @param media is a list of all movies
     * @param genre is the string input genre
     * @return ArrayList<IMedia> returns all media that match the genre inputted
     */
    public static ArrayList<IMedia> searchGenre(ArrayList<IMedia> media, String genre) {
        return searchGenre(media, Genre.valueOf(genre.toUpperCase()));
    }

    /**
     * This method searches through all media and returns the medias that match the genre query and are not age restricted
     *
     * @param media is a list of all movies
     * @param genre is the inputted genre of type IGenre
     * @return ArrayList<IMedia> returns all media that match the genre inputted and that are not age restricted
     */
    public static ArrayList<IMedia> searchGenreAgeRestricted(ArrayList<IMedia> media, IGenre genre) {
        return media.stream().filter(m -> m.getGenres().contains(genre) && !genre.isAgeRestricted()).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * This method is an overload of the previous method and uses String genre rather than IGenre
     *
     * @param media is a list of all movies
     * @param genre is the string input genre
     * @return ArrayList<IMedia> returns all media that match the genre inputted
     */
    public static ArrayList<IMedia> searchGenreAgeRestricted(ArrayList<IMedia> media, String genre) {
        return searchGenreAgeRestricted(media, Genre.valueOf(genre.toUpperCase()));
    }

    /**
     * Searches all series by title and returns series with matching titles
     *
     * @param series is a list of all series
     * @param title is the string input query
     * @return ArrayList<ISeries> returns all series that match the title input
     */
    public static ArrayList<ISeries> searchSeriesTitle(ArrayList<ISeries> series, String title) {
        return series.stream().filter(s -> s.getTitle().toLowerCase().contains(title.toLowerCase())).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * This method also searches series by title, but it is required you write the exact name correctly to find one exact series
     *
     * @param series is a list of all series
     * @param title is the exact title that matches a series
     * @return ISeries returns a single series that matches the string input
     */
    public static ISeries searchSeriesTitleSingle(ArrayList<ISeries> series, String title) {
        return series.stream().filter(s -> s.getTitle().equals(title)).findFirst().orElse(null);
    }

    /**
     * This method searches all movies by title and returns all movies that match the string input
     *
     * @param movies is a list of all movies
     * @param title is string title (that doesnt have to be the full complete name of any movie)
     * @return ArrayList<IMovie> returns a list of movies that match or have part of the queried title
     */
    public static ArrayList<IMovie> searchMovieTitle(ArrayList<IMovie> movies, String title) {
        return movies.stream().filter(m -> m.getTitle().toLowerCase().contains(title.toLowerCase())).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * This method queries through all movies and returns one movie that matches the exact string title
     *
     * @param movies is a list of all movies
     * @param title is an exact string input
     * @return IMovie returns a single movie that matches the exact name of the string title
     */
    public static IMovie searchMovieTitleSingle(ArrayList<IMovie> movies, String title) {
        return movies.stream().filter(m -> m.getTitle().equals(title)).findFirst().orElse(null);
    }

    /**
     * This method searches all media by title and returns the media that matches or has part of string title
     *
     * @param media is a list of all media
     * @param title is the input string that doesnt have to be an exact name of a media
     * @return ArrayList<IMedia> returns the media that matches the string title
     */
    public static ArrayList<IMedia> searchTitle(ArrayList<IMedia> media, String title) {
        return media.stream().filter(m -> m.getTitle().toLowerCase().contains(title.toLowerCase())).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * This method searches through all movies by rating and returns the movies with matching or higher rating
     *
     * @param series is a list of all series
     * @param minimumRating is the minimum desired rating of any series
     * @return ArrayList<ISeries> returns a list of series that have said rating or higher
     */
    public static ArrayList<ISeries> searchSeriesRating(ArrayList<ISeries> series, float minimumRating) {
        return series.stream().filter(s -> s.getRating() >= minimumRating).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * This method queries through all movies and returns the movies that match or have higher ratings than float minimumRating
     *
     * @param movies is a list of all movies
     * @param minimumRating is the minimum desired rating
     * @return ArrayList<IMovie> returns all movies that have the desired rating or higher
     */
    public static ArrayList<IMovie> searchMovieRating(ArrayList<IMovie> movies, float minimumRating) {
        return movies.stream().filter(m -> m.getRating() >= minimumRating).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * This method searches through all media by rating
     *
     * @param media is a list of all media
     * @param minimumRating is the minimum desired rating
     * @return ArrayList<IMedia> returns all media with matching rating or higher rating
     */
    public static ArrayList<IMedia> searchRating(ArrayList<IMedia> media, float minimumRating) {
        return media.stream().filter(m -> m.getRating() >= minimumRating).collect(Collectors.toCollection(ArrayList::new));
    }
}