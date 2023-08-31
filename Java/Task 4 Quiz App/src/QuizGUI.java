import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class QuizGUI {
    private JFrame frame;
    private Boolean firstTime = true;
    private int marks = 0;
    private JLabel question;
    private ArrayList<JRadioButton> options;
    private JButton submit;
    private JLabel time;
    private ButtonGroup bg;
    private JLabel result;

    private Quiz quiz;
    private String currentAnswer;
    private Timer timer;


    public QuizGUI() {
        frame = new JFrame();
        question = new JLabel();
        options = new ArrayList<JRadioButton>();
        submit = new JButton("submit");
        time = new JLabel();
        bg = new ButtonGroup();
        quiz = new Quiz();
        quiz.addDummyQuestions();
        result = new JLabel();
        timer = new Timer(60, time, submit);
    }

    public void setupUI() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(400, 500);

        // add components
        frame.add(time);
        frame.add(question);
        frame.add(result);
        //add options
        for(int i=0; i<3; ++i) {
            JRadioButton rb = new JRadioButton("Option " + (i+1));
            options.add(rb);
            frame.add(rb);
            bg.add(rb);
        }
        // submit button
        frame.add(submit);


        // setting bounds
        time.setBounds(250, 20, 100, 30);
        question.setBounds(30, 80, 340, 30);
        int y = 120;
        for(JRadioButton option : options) {
            option.setBounds(40, y, 320, 30);
            y += 40;
        }
        submit.setBounds(250, y, 100, 30);
        result.setBounds(30, y, 150,30);

        // setting dummy text
        this.addQuestion();
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(var option : options) {
                    if(option.isSelected()){
                        if(option.getText() == currentAnswer){
                            result.setText("Prev question : Correct");
                            ++marks;
                        }else{
                            result.setText("prev question : Wrong");
                        }
                    }
                }
                timer.reset();
                addQuestion();
            }
        });
        frame.setVisible(true);

    }

    public void  addQuestion(){
        Question question = quiz.getQuestion();

        currentAnswer = (question != null) ? question.getAnswer() : null;
        bg.clearSelection();
        if(question != null) {
            this.question.setText(question.getQuestionStatement());
            for(int i=0; i<options.size(); ++i) {
                options.get(i).setText(question.getOptions()[i]);
            }
        }else{
            timer.stopTimer();
            JOptionPane.showMessageDialog(frame, "Quiz ended, your score is " + marks + "/10");
            frame.dispose();
            return;
        }
        timer.start();
    }
}
