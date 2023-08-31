import java.util.ArrayList;

public class Quiz {
    private ArrayList<Question> questions;

    public Quiz() {
        questions = new ArrayList<Question>();
    }
    public Quiz(ArrayList<Question> qs) {
        questions = qs;
    }

    public ArrayList<Question> getAllQuestions() {
        return questions;
    }

    public void addQuestion(Question q) {
        questions.add(q);
    }

    public void addDummyQuestions() {
        questions.add(new Question("What does HTML stand for?", new String[]{"Hyper Text Markup Language", "Home Tool Markup Language", "Hyperlink and Text Markup Language"}, 0));
        questions.add(new Question("Which programming language is known as the 'mother of all languages'?", new String[]{"C", "Java", "Assembly"}, 2));
        questions.add(new Question("What is the result of 2 + 3 * 5?", new String[]{"25", "17", "13"}, 1));
        questions.add(new Question("Which data structure uses the 'Last In, First Out' (LIFO) principle?", new String[]{"Queue", "Stack", "Array"}, 1));
        questions.add(new Question("What is the extension of a Python source file?", new String[]{".txt", ".py", ".java"}, 1));
        questions.add(new Question("What keyword is used to define a constant variable in Java?", new String[]{"const", "final", "let"}, 1));
        questions.add(new Question("In JavaScript, what function is used to print something to the console?", new String[]{"console.log()", "print()", "log()"}, 0));
        questions.add(new Question("Which sorting algorithm has the worst-case time complexity of O(n^2)?", new String[]{"Merge Sort", "Quick Sort", "Bubble Sort"}, 2));
        questions.add(new Question("What is the binary representation of the decimal number 10?", new String[]{"1010", "1100", "1111"}, 0));
        questions.add(new Question("What does SQL stand for?", new String[]{"Structured Query Language", "Sequential Query Language", "Simple Query Language"}, 0));

    }

    public Question getQuestion() {
        if(!questions.isEmpty()) {
            Question q = questions.get(0);
            questions.remove(0);
            return q;
        }
        return null;
    }

}
