package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Deposit extends ATM_Operations {
    JFrame frame;
    JTextField input;
    JButton submit;

    public Deposit() {
        frame = new JFrame();
        input = new JTextField();
        submit = new JButton("Submit");
    }
    @Override
    public void setupUI() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Deposit");
        frame.setLayout(null);
        frame.setSize(400, 500);
        input.setBounds(100, 150, 200, 40);
        submit.setBounds(150, 220, 100, 40);
        frame.add(input);
        frame.add(submit);

        input.addFocusListener(new InputFocusAdapter(input, "Enter amount"));
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                input.requestFocus();
                submit.requestFocus();
            }
        });

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    double amount = Double.parseDouble(input.getText());
                    if(ATM_GUI.account.deposit(amount)){
                        JOptionPane.showMessageDialog(null, "Deposit Successful. New balance " + ATM_GUI.account.getBalance());
                    }else {
                        JOptionPane.showMessageDialog(null, "Wrong amount entered. Please try again");
                    }

                }catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Please enter numbers");
                }
                frame.dispose();


            }
        });

        frame.setVisible(true);

    }

    public static void main(String[] args) {
        new Withdraw().setupUI();
    }
}


