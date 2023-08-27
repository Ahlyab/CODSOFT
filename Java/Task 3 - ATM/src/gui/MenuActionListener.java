package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuActionListener implements ActionListener {

    private ATM_Operations newFrame;

    public MenuActionListener(ATM_Operations newFrame) {
        this.newFrame = newFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        newFrame.setupUI();
    }
}
