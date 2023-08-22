import java.util.Random;
public class NumberGenerator {
    private int counter;
    private Random random;

    private int target;
    public NumberGenerator() {
        counter = 1;
        random = new Random();
    }

    public void createTarget() {
        target = random.nextInt(100) + 1;
    }

    private String matchTarget(int target) {
        ++counter;
        if(this.target == target) {
            return "1";
        }else if(this.target < target){
                return "Too High";
        }else{
            return "Too Low";
        }
    }

    public String match(int target) {
        if(hasExceeded()){
            return "You have exceeded the try limit";
        }
        return matchTarget(target);
    }

    public Boolean hasExceeded() {
        return counter > 5;
    }

    public int getTarget() {
        return target;
    }


}
