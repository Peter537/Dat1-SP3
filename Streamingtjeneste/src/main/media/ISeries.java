package main.media;

import java.util.ArrayList;

public interface ISeries extends IMedia {

    /**
     * As different Watchables can have multiple years, this method returns the year the series started.
     *
     * @return int The year the series started
     */
    int getStartYear();

    /**
     * As different Watchables can have multiple years, this method returns the year the series ended.
     *
     * @return int The year the series ended
     */
    int getEndYear();

    /**
     * This method will return the seasons the series has.
     *
     * @return ArrayList<Season> The seasons of the series
     */
    ArrayList<Season> getSeasons();
}