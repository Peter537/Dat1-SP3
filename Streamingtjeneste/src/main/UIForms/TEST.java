package main.UIForms;

import main.UIForms.IUI;

import javax.swing.*;

public class TEST {
    private JPanel panel1;

    public JPanel getPanel() {
        return panel1;
    }

    public void updatePane(IUI ui, JFrame frame) {
        frame.setContentPane(ui.getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
