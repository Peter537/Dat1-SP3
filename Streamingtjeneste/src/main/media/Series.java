package main.media;

import main.genre.IGenre;

import java.util.ArrayList;

public class Series extends AWatchable implements ISeries {

    private final int startYear;
    private final int endYear;
    private final ArrayList<Season> seasons;

    /**
     * Constructor for Series
     * <p>
     * This constructor creates series objects paired with an arraylist of seasons
     *
     * @param id represents the series-watchable's ID
     * @param title represents series title
     * @param startYear represents the start-year of airing the series
     * @param endYear represents the end-year of airing the series
     * @param rating represesnts the rating of the series
     * @param genres represents the title of the series
     * @param seasons represents the seasons-data of the series
     */
    public Series(int id, String title, int startYear, int endYear, float rating, ArrayList<IGenre> genres, ArrayList<Season> seasons) {
        super(id, title, rating, genres);
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
                "id=" + getID() +
                ", title='" + getTitle() + '\'' +
                ", startYear=" + getStartYear() +
                ", endYear=" + ((endYear == -1) ? "ongoing" : getEndYear()) +
                ", rating=" + getRating() +
                ", genres=" + getGenres() +
                ", seasons=" + getSeasons() +
                '}';
    }
}