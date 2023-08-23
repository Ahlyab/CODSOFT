import java.util.Random;
public class NumberGenerator {
    private int counter;
    private Random random;
    private int countWin;

    private int target;
    public NumberGenerator() {
        counter = 1;
        random = new Random();
        countWin = 0;
    }

    public void createTarget() {
        target = random.nextInt(100) + 1;
    }

    private String matchTarget(int target) {
        ++counter;
        String res = null;
        if(this.target == target) {
            ++countWin;
            res = "You guessed it right!";
        }else if(this.target < target){
                res =  "Too High";
        }else{
            res =  "Too Low";
        }
//        ++counter;
        return res;
    }

    public String match(int target) {
        if(hasExceeded()){
            return "You have exceeded the try limit. Answer : " + getTarget() ;
        }
//        ++counter;
        return matchTarget(target);
    }

    public Boolean hasExceeded() {
        return counter > 5;
    }

    public int getTarget() {
        return target;
    }

    public void resetGame() {
        counter = 1;
        createTarget();
    }

    public int getCounter() {
        return counter;
    }
    public int getCountWin() {return countWin;}

}
