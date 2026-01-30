package assignments.assignment010.atmCashWithdrawal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class BankAccount {
    private int balance = 5000;

    public synchronized void withdraw(int amount, String user) throws InterruptedException {
        if (balance >= amount) {
            System.out.println(user + " Withdrawal processing...");
            Thread.sleep(100);
            balance -= amount;
            System.out.println(user + " New Balance: " + balance);
        } else {
            System.out.println(user + " Insufficient funds. Balance: " + balance);
        }
    }
}

class Transaction implements Runnable {
    private final BankAccount account;
    private final int amount;
    private final String user;

    public Transaction(BankAccount account, int amount, String user) {
        this.account = account;
        this.amount = amount;
        this.user = user;
    }

    public void run() {
        try {
			account.withdraw(amount, user);
		} catch (InterruptedException e) {}
    }
}

public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(new Transaction(account, 3000, "Ragul Prasath"));
        executor.execute(new Transaction(account, 4000, "Rohith Vishwa"));

        executor.shutdown();
    }
}


