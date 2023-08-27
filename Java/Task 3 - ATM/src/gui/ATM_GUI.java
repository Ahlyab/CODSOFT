package gui;
import core.Account;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ATM_GUI {
    protected static Account account = new Account();
    private JFrame frame;
    private JTextField name;
    private JTextField balance;
    private JButton login;

    public ATM_GUI() {
        frame = new JFrame();
        name = new JTextField();
        balance = new JTextField();
        login = new JButton("Login");

    }

    public void setupUI(){
        frame.setTitle("ATM login");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        name.setBounds(100, 100, 200, 30);
        balance.setBounds(100, 150, 200, 30);
        login.setBounds(150, 200, 100, 30);

        frame.add(name);
        frame.add(balance);
        frame.add(login);
        frame.setLayout(null);

        name.addFocusListener(new InputFocusAdapter(name, "Enter account name"));
        balance.addFocusListener(new InputFocusAdapter(balance, "Enter amount"));

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                name.requestFocus();
                balance.requestFocus();
                login.requestFocus();
            }
        });

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String n = "";
                double amount = 0.00;
                try{
                    n = name.getText();
                    amount = Double.parseDouble(balance.getText());
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Please enter correct amount");
                }
                account.setName(n);
                account.setBalance(amount);

                frame.dispose();
                new Menu().setupUI();


            }
        });


        frame.setVisible(true);
    }
}


