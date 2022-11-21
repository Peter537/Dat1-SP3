package main.media;

import java.util.ArrayList;

public interface ISeries extends IMedia {

    // this interface adds a start year and end year as series arent single-date watchables.


    int getStartYear();

    int getEndYear();

    // not all watchables have seasons, but series do.
    ArrayList<Season> getSeasons();
}