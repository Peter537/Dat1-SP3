package media;

import java.util.ArrayList;

public interface ISeries extends IMedia {
    int getStartYear();
    int getEndYear();
    ArrayList<ISeason> getSeasons();
}