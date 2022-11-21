package main.media;

public interface IMovie extends IMedia {

   // movies dont have a start-to-end year, but it has a releasedate.
    int getYear();
}