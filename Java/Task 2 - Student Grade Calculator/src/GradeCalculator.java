public class GradeCalculator {
    private int[] marks;
    private int size;
    private double avg;
    private double percentage;
    private char grade;
    private int index;

    public double getAvg() {
        return avg;
    }

    public double getPercentage() {
        return percentage;
    }

    public char getGrade() {
        return grade;
    }

    GradeCalculator(){
        size = 3;
        marks = new int[size];
        index = 0;
        avg = 0.00;
        percentage = 0.00;
        percentage = '\0';
    }

    public void addSubjectMarks(int mark) {
        try{
            try{
                if(mark < 0 || mark > 100){
                    throw new NumberOutOfRange("Marks should be between 0 - 100");
                }else{
                    marks[index] = mark;
                    ++index;
                }
            } catch (NumberOutOfRange e) {
                throw new RuntimeException(e);
            }

        }catch (ArrayIndexOutOfBoundsException  ex) {
            throw new RuntimeException(ex);
        }
    }

    public void calculateAvg() {
        for(int mark : marks) {
            avg += mark;
        }
        avg =  avg/marks.length;
    }

    public void calculatePercentage() {
        int sum = 0;
        for(int mark: marks) {
            sum += mark;
        }
        this.percentage =  (sum*100.00)/300.00;
    }

    public void calculateGrade() {
        if(percentage >= 90 && percentage <= 100){
            percentage = 'A';
        }else if(percentage >= 80 && percentage < 90){
            percentage = 'B';
        }else if(percentage >= 70 && percentage < 80){
            percentage = 'C';
        }else if(percentage >= 60 && percentage < 70){
            percentage = 'D';
        }else if(percentage < 60){
            percentage = 'F';
        }

    }
}
