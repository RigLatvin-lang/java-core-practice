import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentBank {
    private final Map<Integer, BankAccount> accounts = new ConcurrentHashMap<>();
    private final AtomicInteger idgenerator = new AtomicInteger();

    public BankAccount createAccount(double initialBalance) {
        int id = idgenerator.incrementAndGet();
        BankAccount account = new BankAccount(id, initialBalance);
        accounts.put(id, account);
        return account;
    }

    public void transfer(BankAccount from, BankAccount to, double amount) {
        BankAccount first = (from.getId() < to.getId()) ? from : to;
        BankAccount second = (first == from) ? to : from;
        
        synchronized (first) {
            synchronized (second) {
                from.withdraw(amount);
                to.deposit(amount);
            }
        }
    }

    public double getTotalBalance() {
        double total = 0.0;
        for (BankAccount account : accounts.values()) {
            total += account.getBalance();
        }
    return total;
    }
}
