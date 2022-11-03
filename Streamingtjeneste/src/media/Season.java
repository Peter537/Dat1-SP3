package media;

public class Season extends ASeason {
    public Season(int seasonNumber, int episodeCount) {
        super(seasonNumber, episodeCount);
    }

    @Override
    public String toString() {
        return "Season " + getSeasonNumber() + " (" + getEpisodeCount() + " episodes)";
    }
}