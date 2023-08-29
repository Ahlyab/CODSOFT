import java.util.Objects;

public class Question {
    private String questionStatement;
    private String options[];
    private int indexOfAnswer;

    public Question(String stmt, String[]options, int indexOfAnswer) {
        this.questionStatement = stmt;
        this.options = options;
        if(indexOfAnswer >= 0 && indexOfAnswer < options.length){
            indexOfAnswer = indexOfAnswer;
        }else{
            throw new IndexOutOfBoundsException(indexOfAnswer);
        }
    }

    public String getQuestionStatement() {
        return questionStatement;
    }

    public String getAnswer() {
        return options[indexOfAnswer];
    }

    public String[] getOptions() {
        return options;
    }

    public Boolean matchAnswer(String answer) {
        return Objects.equals(options[indexOfAnswer], answer);
    }
}
