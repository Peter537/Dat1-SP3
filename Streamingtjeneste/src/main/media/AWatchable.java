package main.media;

import main.genre.IGenre;

import java.util.ArrayList;

public abstract class AWatchable implements IMedia {

    private final int id;
    private final String title;
    private final float rating;
    private final ArrayList<IGenre> genres;

    /**
     * Constructor for AWatchable
     * <p>
     * This constructor represents all watchable media on ChillMedia.
     *
     * @param id represents the ID of the watchable
     * @param title represents the title of the watchable
     * @param rating represents the rating of the watchable
     * @param genres represents the genre(s) of the watchable
     */
    public AWatchable(int id, String title, float rating, ArrayList<IGenre> genres) {
        this.id = id;
        this.title = title;
        this.rating = rating;
        this.genres = genres;
    }

    @Override
    public int getID() {
        return this.id;
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
                "id=" + getID() +
                ", title='" + getTitle() + '\'' +
                ", rating=" + getRating() +
                ", genres=" + getGenres() +
                '}';
    }
}