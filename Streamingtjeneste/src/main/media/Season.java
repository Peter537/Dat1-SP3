package main.media;

public class Season {

    private final int seasonNumber;
    private final int episodeCount;

    /**
     * Constructor for Season
     * <p>
     * As a series is not a watchable on its own, this class represents the exact season/episode of a series' watachable
     * This class is not currently used as we were not given any episode-data.
     *
     * @param seasonNumber represents the exact season of a series
     * @param episodeCount represents the amount of episodes within a season
     */
    public Season(int seasonNumber, int episodeCount) {
        this.seasonNumber = seasonNumber;
        this.episodeCount = episodeCount;
    }

    public int getSeasonNumber() {
        return this.seasonNumber;
    }

    public int getEpisodeCount() {
        return this.episodeCount;
    }

    @Override
    public String toString() {
        return "Season{" +
                "seasonNumber=" + getSeasonNumber() +
                ", episodeCount=" + getEpisodeCount() +
                '}';
    }
}