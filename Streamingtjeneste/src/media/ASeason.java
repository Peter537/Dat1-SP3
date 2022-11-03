package media;

public abstract class ASeason implements ISeason {
    int seasonNumber;
    int episodeCount;

    public ASeason(int seasonNumber, int episodeCount){
        super();
        this.seasonNumber = seasonNumber;
        this.episodeCount = episodeCount;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public int getEpisodeCount() {
        return episodeCount;
    }
}