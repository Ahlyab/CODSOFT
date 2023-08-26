public class Account {
    private String name;
    private double balance;


    Account(){
        this("",0.00);
    }

    Account(String name, double balance){
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Boolean withdraw(double amount) {
        if(amount > balance || amount < 0) {
            return false;
        }
        balance -= amount;
        return true;
    }

    public Boolean deposit(double amount) {
        if(amount < 0){
            return false;
        }
        balance += amount;
        return true;
    }
}

