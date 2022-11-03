package media;

import genre.IGenre;
import media.ISeason;
import media.ISeries;

import java.util.ArrayList;

public class Series extends AWatchable implements ISeries {
    int startYear;
    int endYear;
    ArrayList<ISeason> seasons;

    public Series(String title, int startYear, int endYear, float rating, ArrayList<IGenre> genres, ArrayList<ISeason> seasons) {
        super(title, rating, genres);
        this.startYear = startYear;
        this.endYear = endYear;
        this.seasons = seasons;
    }

    public int getStartYear() {
        return startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public ArrayList<ISeason> getSeasons() {
        return seasons;
    }
}