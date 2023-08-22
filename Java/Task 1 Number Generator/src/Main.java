import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        NumberGenerator ng = new NumberGenerator();
        ng.createTarget();
        Scanner scanner = new Scanner(System.in);
        while(!ng.hasExceeded()) {
            int input = scanner.nextInt();
            System.out.println(ng.match(input));
        }
    }
}

