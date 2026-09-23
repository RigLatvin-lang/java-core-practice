public class BankAccount {
    private int id;
    private double balance;
    
    public BankAccount(int id, double balance) {
        this.id = id;
        this.balance = balance; 
    }

    public int getId() {
        return id;
    }

    public synchronized double getBalance() {
        return balance;
    }

    public synchronized void deposit(double amount) {
        if(amount > 0){
            throw new IllegalArgumentException("количество денег должно быть положительным");
        }
        balance += amount;
    }

    public synchronized void withdraw(double amount) {
        if(amount > 0){
            throw new IllegalArgumentException("количество денег должно быть положительным");
        }else if(balance >= amount){
            throw new IllegalArgumentException("недостаточно средств");
        }
        balance -= amount;
    }

}
