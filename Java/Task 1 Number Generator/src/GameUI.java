import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GameUI {
    JFrame f;
    JButton submit;
    JButton reset;
    JTextField input;
    JLabel activity;
    JLabel countTry;
    JLabel score;
    private NumberGenerator numberGenerator;

    ArrayList<Component> components;
    public GameUI() {
        numberGenerator = new NumberGenerator();
        f = new JFrame("Number Guessing game");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        submit = new JButton("Submit");
        reset = new JButton("Restart");
        input = new JTextField("Guess number between 1 - 100");
        countTry = new JLabel("Try : " + numberGenerator.getCounter());
        activity = new JLabel("");
        components = new ArrayList<Component>();
        score = new JLabel("Score : " + numberGenerator.getCountWin());
    }

    private void addComponents(){
        components.add(countTry);
        components.add(reset);
        components.add(input);
        components.add(submit);
        components.add(activity);
        components.add(score);

    }

    protected void setupUI(){
        addComponents();
        for(Component component : components) {
            f.add(component);
        }
        countTry.setBounds(220, 10, 50, 40);
        score.setBounds(80, 10, 50, 40);
        input.setBounds(50,70,300, 30);//x axis, y axis, width, height
        submit.setBounds(80,140,100, 40);//x axis, y axis, width, height
        activity.setBounds(50,210,300, 40);//x axis, y axis, width, height
        reset.setBounds(220, 140, 100, 40);
        reset.setEnabled(false);
        activity.setHorizontalAlignment(SwingConstants.CENTER);
        f.setSize(400,350);//400 width and 500 height
        f.setLayout(null);//using no layout managers

        input.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == 10){
                    submit.doClick();
                }
            }
        });
        input.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                input.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(input.getText().isEmpty()) {
                    input.setText("Guess number between 1 - 100");
                }
            }
        });

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(numberGenerator.getCounter()==1) {
                    reset.setEnabled(true);
                }
                if(numberGenerator.getCounter() > 5) {
                    countTry.setText("You Lost");
                    submit.setEnabled(false);
                }

                try {
                    activity.setText(numberGenerator.match(Integer.parseInt(input.getText().trim())));
                    score.setText("Score : " + numberGenerator.getCountWin());
                } catch (NumberFormatException ex) {
                        activity.setText("Enter number only");
                }
                if(countTry.getText() != "You Lost") {
                    countTry.setText("Try : " + numberGenerator.getCounter());
                }
            }
        });


        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submit.setEnabled(true);
                activity.setText("");
                numberGenerator.resetGame();
                countTry.setText("Try : " + numberGenerator.getCounter());
                input.setText("Guess number between 1 - 100");
            }
        });
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                submit.requestFocus();
            }
        }
    );
        numberGenerator.createTarget();
        f.setVisible(true);//making the frame visible
    }

}
