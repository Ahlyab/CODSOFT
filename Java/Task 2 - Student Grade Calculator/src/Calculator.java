import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class Calculator {
    private JFrame frame;
    private JTextField subject1;
    private JTextField subject2;
    private JTextField subject3;
    private JButton submit;
    private JLabel avg;
    private JLabel percent;
    private JLabel grade;

    private ArrayList<Component> components;



    public Calculator() {
        frame = new JFrame();

        subject1 = new JTextField();
        subject2 = new JTextField();
        subject3 = new JTextField();
        submit = new JButton("Submit");
        avg = new JLabel();
        percent = new JLabel();
        grade = new JLabel();
        components = new ArrayList<Component>();
        addComponents();
    }

    private void addComponents() {
        components.add(subject1);
        components.add(subject2);
        components.add(subject3);
        components.add(submit);
        components.add(avg);
        components.add(percent);
        components.add(grade);
    }

    private void addComponentsToUI() {
        for(Component component : components) {
            frame.add(component);
        }
    }
    public void setUpUI() {
        frame.setTitle("Grade Calculator");
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        subject1.setBounds(100, 20, 200, 30);
        subject2.setBounds(100, 70, 200, 30);
        subject3.setBounds(100, 120, 200, 30);
        submit.setBounds(150, 170, 100, 40);
        avg.setBounds(80, 220, 100, 30);
        percent.setBounds(190, 220, 100, 30);
        grade.setBounds(300, 220, 100, 30);

        subject1.addFocusListener(new InputFocusAdapter(subject1, "Enter marks of subject 1"));
        subject2.addFocusListener(new InputFocusAdapter(subject2, "Enter marks of subject 2"));
        subject3.addFocusListener(new InputFocusAdapter(subject3, "Enter marks of subject 3"));

        addComponentsToUI();
        frame.setLayout(null);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                subject1.requestFocus();
                subject2.requestFocus();
                subject3.requestFocus();
                submit.requestFocus();
            }
        });


        frame.setVisible(true);
    }
    public static void main(String[] args) {

        Calculator calculator = new Calculator();
        calculator.setUpUI();
    }
}

