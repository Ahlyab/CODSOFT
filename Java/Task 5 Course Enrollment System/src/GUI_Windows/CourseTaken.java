package GUI_Windows;

import javax.swing.*;

public class CourseTaken extends Window {

    private JFrame frame = new JFrame();

    @Override
    public void setupUI() {
        frame.setLayout(null);
        frame.setSize(700,700);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
