import GUI_Windows.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuActionListener implements ActionListener {
    private Window window;
    public MenuActionListener(Window window) {
        this.window = window;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        window.setupUI();
    }
}
