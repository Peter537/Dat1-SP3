package main.UIForms;

import main.ChillMedia;

import javax.swing.*;

public abstract class AUI implements IUI {
    public AUI() {

    }
    public abstract JPanel getPanel();

    public void updatePane(IUI ui) {
        ChillMedia.page.setContentPane(ui.getPanel());
        ChillMedia.page.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ChillMedia.page.pack();
        ChillMedia.page.setVisible(true);
    }
}
