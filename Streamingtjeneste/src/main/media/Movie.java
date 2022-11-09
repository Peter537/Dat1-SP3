package main.media;

import main.genre.IGenre;

import java.util.ArrayList;

public class Movie extends AWatchable implements IMovie {

    private final int year;

    public Movie(String title, float rating, ArrayList<IGenre> genres, int year) {
        super(title, rating, genres);
        this.year = year;
    }

    public int getYear() {
        return this.year;
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