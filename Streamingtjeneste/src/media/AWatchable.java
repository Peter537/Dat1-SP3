package media;

import genre.IGenre;

import java.util.ArrayList;

public abstract class AWatchable implements IMedia {
    String title;
    float rating;
    ArrayList<IGenre> genres;

    public AWatchable(String title, float rating, ArrayList<IGenre> genres) {
        this.title = title;
        this.rating = rating;
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public float getRating() {
        return rating;
    }

    public ArrayList<IGenre> getGenres() {
        return genres;
    }
}