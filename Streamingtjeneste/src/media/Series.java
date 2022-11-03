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

    public int getStartYear() {
        return startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public String toString() {
        return "Series: " + title + " (" + startYear + "-" + endYear + ") with " + seasons.size() + " seasons and a rating of " + rating + " and genres: " + genres + ". \n";
    }
}