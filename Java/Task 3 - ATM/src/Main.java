public class Main {
    public static void main(String[] args) {
        ATM atm = new ATM(new Account("ALi", 500));
        if(atm.deposit(1000)){
            System.out.println("success");
        }else{
            System.out.println("Failed");
        }
    }
}

