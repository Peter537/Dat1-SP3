package media;

import genre.IGenre;

import java.util.ArrayList;

public class Movie extends AWatchable implements IMovie {
    int year;

    public Movie(String title, float rating, ArrayList<IGenre> genres, int year) {
        super(title, rating, genres);
        this.year = year;
    }

    @Override
    public int getYear() {
        return 0;
    }
}