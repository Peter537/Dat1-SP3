package utils;

import media.IMedia;
import media.IMovie;
import media.ISeries;
import utils.data.FileIO;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
//import java.awt.*;

public class UI {
    private JList movieList;
    public JPanel panel;
    private JTabbedPane tabbedPane1;
    private JList serieList;

    public UI() {
        setData(this);
    }

    public void setData(UI data) {
        FileIO fileIO = new FileIO();
        ArrayList<IMovie> movies = fileIO.loadMovies();
        ArrayList<ISeries> series = fileIO.loadSeries();

        movies.sort(Comparator.comparing(IMedia::getTitle));
        series.sort(Comparator.comparing(IMedia::getTitle));

        data.movieList.setListData(movies.toArray());
        data.serieList.setListData(fileIO.loadSeries().toArray());
    }

    public void getData(UI data) {

    }

    public boolean isModified(UI data) {
        return false;
    }
}
