package main.UIForms;

import main.ChillMedia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

public class ChooseList extends AUI {

    private JPanel panel;
    private JButton seriesButton;
    private JButton movieButton;
    private JLabel icon;

    public ChooseList(JFrame page) {
        super(page);
        setData(this);
        movieButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                super.mouseClicked(e);
                IUI ui = new UI(ChillMedia.page);
                updatePane(ui);
            }
        });
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }

    public void setData(ChooseList data) {

        Image image = new ImageIcon("Data/Illustration9.jpg").getImage();
        Icon icon = new ImageIcon(image);
        data.icon.setIcon(icon);
    }

    public void getData(ChooseList data) {
    }

    public boolean isModified(ChooseList data) {
        return false;
    }
}
