package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuActionListener implements ActionListener {

    private JFrame currentFrame;
    private ATM_Operations newFrame;

    public MenuActionListener(JFrame currentFrame, ATM_Operations newFrame) {
        this.currentFrame = currentFrame;
        this.newFrame = newFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        currentFrame.dispose();
        newFrame.setupUI();
    }
}
