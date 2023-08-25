import javax.swing.*;

// Todo: Implement GUI for the application
public class Main {
    public static void main(String[] args) {
        GradeCalculator gradeCalculator = new GradeCalculator();
        gradeCalculator.addSubjectMarks(90);
        gradeCalculator.addSubjectMarks(80);
        gradeCalculator.addSubjectMarks(100);
        gradeCalculator.calculatePercentage();
        gradeCalculator.calculateAvg();
        gradeCalculator.calculateGrade();
        System.out.println(gradeCalculator.getPercentage());
        System.out.println(gradeCalculator.getAvg());
        System.out.println(gradeCalculator.getPercentage());
    }
}

