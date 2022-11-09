package utils.UIForms;

import javax.swing.*;

public class TEST implements IUI {
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
