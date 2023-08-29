import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
//        Question question = new Question("Is java case sensitive?", new String[]{"yes", "no", "maybe"},0);
//
//        String[] options = question.getOptions();
//        System.out.println(question.getQuestionStatement());
//        for(String option : options) {
//            System.out.println(option);
//        }
//        Scanner scanner = new Scanner(System.in);
//        int ans = scanner.nextInt();
//
//        if(options[ans] == question.getAnswer()) {
//            System.out.println("Correct");
//        }else {
//            System.out.println("Wrong");
//        }

        Timer timer = new Timer(20);
        timer.startTimer();
//        timer.stopTimer();
        for(int i=0; i<21; ++i) {
            System.out.println(i);
        }
        TimeUnit.SECONDS.sleep(5);

        timer.stopTimer();

    }
}

