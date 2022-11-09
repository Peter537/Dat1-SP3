package main.UIForms;

import javax.swing.*;

public abstract class AUI implements IUI {
    private JPanel panel;
    protected JFrame page;

    public AUI(JFrame page) {
        this.page = page;
    }
    public JPanel getPanel() {
        return null;
    }

    public void updatePane(IUI ui) {
        page.setContentPane(ui.getPanel());
        page.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        page.pack();
        page.setVisible(true);
    }
}
