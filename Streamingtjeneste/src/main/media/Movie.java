package main.media;

import main.genre.IGenre;

import java.util.ArrayList;

public class Movie extends AWatchable implements IMovie {

    private final int year;

    /**
     * Constructor for Movie
     * <p>
     * extends the scheme of AWatchable and uses this + IMovie into constructor
     * This constructor creates new movies as java objects
     *
     * @param id represents the ID of the watchable
     * @param title represents the title of the watchable
     * @param rating represents the rating of the watchable
     * @param genres represents the genre(s) of the watchable
     * @param year represents release-year of the movie
     */
    public Movie(int id, String title, float rating, ArrayList<IGenre> genres, int year) {
        super(id, title, rating, genres);
        this.year = year;
    }

    @Override
    public int getYear() {
        return this.year;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + getID() +
                ", title='" + getTitle() + '\'' +
                ", rating=" + getRating() +
                ", genres=" + getGenres() +
                ", year=" + getYear() +
                '}';
    }
}