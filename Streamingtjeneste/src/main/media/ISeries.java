package main.media;

import java.util.ArrayList;

public interface ISeries extends IMedia {

    int getStartYear();

    int getEndYear();

    ArrayList<Season> getSeasons();
}