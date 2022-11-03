import java.util.ArrayList;

public interface ISeries {
    int getStartYear();
    int getEndYear();
    ArrayList<ISeason> getSeasons();
}
