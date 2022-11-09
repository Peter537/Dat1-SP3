package utils.UIForms;

import media.IMedia;
import media.IMovie;
import media.ISeries;
import utils.data.FileIO;

import javax.swing.*;
import javax.swing.text.html.ImageView;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
//import java.awt.*;

public class UI extends AUI {
    private JList movieList;
    public JPanel panel;
    private JTabbedPane tabbedPane1;
    private JList serieList;
    private JLabel label1;

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
        data.serieList.setListData(series.toArray());

        Image image = new ImageIcon("Data/filmplakater/12 Angry Men.jpg").getImage();

        Icon icon = new ImageIcon(image);

        data.label1.setIcon(icon);
    }

    public void getData(UI data) {

    }

    public boolean isModified(UI data) {
        return false;
    }


}
