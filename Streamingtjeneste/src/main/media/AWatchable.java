package main.media;

import main.genre.IGenre;

import java.util.ArrayList;

public abstract class AWatchable implements IMedia {

    private final String title;
    private final float rating;
    private final ArrayList<IGenre> genres;

    /**
     * Constructor for AWatchable
     * <p>
     * KOMMENTAR_TIL_KONSTRUKTØREN_HER
     *
     * @param title
     * @param rating
     * @param genres
     */
    public AWatchable(String title, float rating, ArrayList<IGenre> genres) {
        this.title = title;
        this.rating = rating;
        this.genres = genres;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public float getRating() {
        return this.rating;
    }

    @Override
    public ArrayList<IGenre> getGenres() {
        return this.genres;
    }

    @Override
    public String toString() {
        return "AWatchable{" +
                "title='" + getTitle() + '\'' +
                ", rating=" + getRating() +
                ", genres=" + getGenres() +
                '}';
    }
}