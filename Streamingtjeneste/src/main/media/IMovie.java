package main.media;

public interface IMovie extends IMedia {

    /**
     * As different Watchables can have multiple years, this method returns the year the movie was released.
     *
     * @return int The release date of the movie
     */
    int getYear();
}