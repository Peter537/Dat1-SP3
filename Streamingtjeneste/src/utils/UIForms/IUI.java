package utils.UIForms;

import javax.swing.*;

public interface IUI {
    public JPanel getPanel();
    public void updatePane(IUI ui, JFrame frame);
}
