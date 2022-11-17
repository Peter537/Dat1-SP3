package main.genre;

public enum Genre implements IGenre {
    CRIME(true, true, true),
    DRAMA(true, true, true),
    BIOGRAPHY(true, true, false),
    SPORT(true, true, false),
    HISTORY(true, true, false),
    ROMANCE(true, true, false),
    WAR(true, true, true),
    MYSTERY(true, true, false),
    ADVENTURE(true, true, false),
    FAMILY(true, true, false),
    FANTASY(true, true, false),
    THRILLER(true, true, true),
    HORROR(true, true, true),
    FILM_NOIR(false, true, false),
    ACTION(true, true, false),
    SCI_FI(true, true, false),
    COMEDY(true, true, false),
    MUSICAL(true, false, false),
    WESTERN(true, true, true),
    MUSIC(true, false, false),
    TALK_SHOW(false, true, false),
    DOCUMENTARY(false, true, false),
    ANIMATION(false, true, false);

    private final boolean isMovieGenre;
    private final boolean isSeriesGenre;
    private final boolean isAgeRestricted;

    Genre(boolean isMovieGenre, boolean isSeriesGenre, boolean isAgeRestricted) {
        this.isMovieGenre = isMovieGenre;
        this.isSeriesGenre = isSeriesGenre;
        this.isAgeRestricted = isAgeRestricted;
    }

    @Override
    public boolean isMovieGenre() {
        return isMovieGenre;
    }

    @Override
    public boolean isSeriesGenre() {
        return isSeriesGenre;
    }

    @Override
    public boolean isAgeRestricted() {
        return isAgeRestricted;
    }
}