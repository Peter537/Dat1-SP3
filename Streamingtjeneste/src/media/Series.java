package media;

import genre.IGenre;

import java.util.ArrayList;

public class Series extends AWatchable implements ISeries {
    int startYear;
    int endYear;
    ArrayList<Season> seasons;

    public Series(String title, int startYear, int endYear, float rating, ArrayList<IGenre> genres, ArrayList<Season> seasons) {
        super(title, rating, genres);
        this.startYear = startYear;
        this.endYear = endYear;
        this.seasons = seasons;
    }

    public Series() {
        super("", 0, new ArrayList<>());
    }

    public int getStartYear() {
        return startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }
}