package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {
    private JFrame frame;
    private JButton withdraw;
    private JButton deposit;
    private JButton checkBalance;

    Menu() {
        frame = new JFrame();
        withdraw = new JButton("Withdraw");
        deposit = new JButton("Deposit");
        checkBalance = new JButton("Check Balance");
    }

    public void setupUI(){
        frame.setTitle("Menu");
        frame.setSize(400, 500);
        withdraw.setBounds(100, 100, 200, 50);
        deposit.setBounds(100, 200, 200, 50);
        checkBalance.setBounds(100, 300, 200, 50);
        frame.add(withdraw);
        frame.add(deposit);
        frame.add(checkBalance);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        withdraw.addActionListener(new MenuActionListener(frame, new Withdraw()));
        deposit.addActionListener(new MenuActionListener(frame, new Deposit()));
        checkBalance.addActionListener(new MenuActionListener(frame, new CheckBalance()));
        
    }

    public static void main(String[] args) {
        new Menu().setupUI();
    }

}

