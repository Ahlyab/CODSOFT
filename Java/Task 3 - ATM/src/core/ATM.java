package core;

public class ATM {
    Account account;

    ATM(Account acc) {
        account = acc;
    }

    public boolean withdraw(double amount) {
        return account.withdraw(amount);
    }

    public boolean deposit(double amount) {
        return account.deposit(amount);
    }

}
