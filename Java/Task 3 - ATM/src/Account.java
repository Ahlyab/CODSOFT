public class Account {
    private String name;
    private  long CC;
    private short pin;
    private double balance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCC() {
        return CC;
    }

    public void setCC(long CC) {
        this.CC = CC;
    }

    public short getPin() {
        return pin;
    }

    public void setPin(short pin) {
        this.pin = pin;
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

