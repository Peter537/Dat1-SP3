package main.UIForms;

import main.media.IMedia;
import main.media.IMovie;
import main.media.Movie;
import main.utils.data.FileIO;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Movies extends AUI {
    private JPanel moviePanel;
    private JTabbedPane tabbedPane1;
    private JPanel allMovies;
    private JTextPane textPane1;

    public Movies() {
        setData(this);
    }

    public void setData(Movies data) {
        if (FileIO.movies.size() < 1) {
            FileIO movies = new FileIO();
            movies.loadMovies();
        }
        ArrayList<IMovie> movieList = FileIO.movies;
        movieList = movieList.stream().sorted(Comparator.comparing(IMedia::getTitle)).collect(Collectors.toCollection(ArrayList::new));

        ArrayList<JLabel> labels = new ArrayList<>();

//        for (int i = 1; tabbedPane1.getTabCount() >= i; i++) {
            int width = 200;
            int x = 0;
            int y = 0;
            for (IMovie movie : movieList) {
                Image image = new ImageIcon("Data/" + movie.getTitle() + ".jpg").getImage();
                Icon icon = new ImageIcon(image);
                JLabel label = new JLabel(icon);
                label.setEnabled(true);
                label.setVisible(true);
                label.setHorizontalAlignment(SwingConstants.LEFT);
                label.setVerticalAlignment(SwingConstants.CENTER);
                label.setHorizontalTextPosition(SwingConstants.LEFT);
                label.setFont(new Font("Arial", Font.PLAIN, 12));
//                label.setMinimumSize(new Dimension(1000, 1000));
//                label.setPreferredSize(new Dimension(1000, 1000));
//                label.setMaximumSize(new Dimension(1000, 1000));
                label.setSize(new Dimension(width, 100));
                label.setLocation(x, y*12);
//
//
//                label.setIcon(icon);

                labels.add(label);

                x += width;
                if (x >= 1000) {
                    x = 0;
                    y++;
                }
            }
//        }
        for (JLabel label : labels) {
            data.textPane1.add(label);
        }

    }

    public void getData(Movies data) {
    }

    public boolean isModified(Movies data) {
        return false;
    }

    public JPanel getPanel() {
        return moviePanel;
    }
}
