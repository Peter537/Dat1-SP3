package media;

public class Season {

    private final int seasonNumber;
    private final int episodeCount;

    public Season(int seasonNumber, int episodeCount) {
        this.seasonNumber = seasonNumber;
        this.episodeCount = episodeCount;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public int getEpisodeCount() {
        return episodeCount;
    }

    @Override
    public String toString() {
        return "Season{" +
                "seasonNumber=" + getSeasonNumber() +
                ", episodeCount=" + getEpisodeCount() +
                '}';
    }
}