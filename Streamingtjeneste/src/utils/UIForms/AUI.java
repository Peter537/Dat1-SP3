package utils.UIForms;

import javax.swing.*;

public class AUI implements IUI {
    public JPanel panel;
    public JPanel getPanel() {
        return panel;
    }

    public void updatePane(IUI ui, JFrame frame) {
        frame.setContentPane(ui.getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
