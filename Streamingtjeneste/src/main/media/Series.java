package main.media;

import main.genre.IGenre;

import java.util.ArrayList;

public class Series extends AWatchable implements ISeries {

    private final int startYear;
    private final int endYear;
    private final ArrayList<Season> seasons;

    public Series(String title, int startYear, int endYear, float rating, ArrayList<IGenre> genres, ArrayList<Season> seasons) {
        super(title, rating, genres);
        this.startYear = startYear;
        this.endYear = endYear;
        this.seasons = seasons;
    }

    @Override
    public int getStartYear() {
        return this.startYear;
    }

    @Override
    public int getEndYear() {
        return this.endYear;
    }

    @Override
    public ArrayList<Season> getSeasons() {
        return this.seasons;
    }

    @Override
    public String toString() {
        return "Series{" +
                "title='" + getTitle() + '\'' +
                ", rating=" + getRating() +
                ", genres=" + getGenres() +
                ", startYear=" + getStartYear() +
                ", endYear=" + ((endYear == -1) ? "ongoing" : getEndYear()) +
                ", seasons=" + getSeasons() +
                '}';
    }
}