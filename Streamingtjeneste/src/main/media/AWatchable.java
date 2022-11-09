package main.media;

import main.genre.IGenre;

import java.util.ArrayList;

public abstract class AWatchable implements IMedia {

    private final String title;
    private final float rating;
    private final ArrayList<IGenre> genres;

    public AWatchable(String title, float rating, ArrayList<IGenre> genres) {
        this.title = title;
        this.rating = rating;
        this.genres = genres;
    }

    public String getTitle() {
        return this.title;
    }

    public float getRating() {
        return this.rating;
    }

    public ArrayList<IGenre> getGenres() {
        return this.genres;
    }
}