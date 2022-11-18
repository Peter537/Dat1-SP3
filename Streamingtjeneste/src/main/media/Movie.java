package main.media;

import main.genre.IGenre;

import java.util.ArrayList;

public class Movie extends AWatchable implements IMovie {

    private final int year;
    private int id;
    /**
     * Constructor for Movie
     * <p>
     * KOMMENTAR_TIL_KONSTRUKTØREN_HER
     *
     * @param title
     * @param rating
     * @param genres
     * @param year
     */
    public Movie(int id, String title, float rating, ArrayList<IGenre> genres, int year) {
        super(title, rating, genres);
        this.year = year;
        this.id = id;
    }

    @Override
    public int getYear() {
        return this.year;
    }

    public int getID() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + getTitle() + '\'' +
                ", rating=" + getRating() +
                ", genres=" + getGenres() +
                ", year=" + getYear() +
                '}';
    }
}