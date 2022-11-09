package main.media;

import main.genre.IGenre;

import java.util.ArrayList;

public interface IMedia {

    String getTitle();

    float getRating();

    ArrayList<IGenre> getGenres();
}