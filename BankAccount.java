import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {

    private double balance;

    private final ReentrantLock lock =
        new ReentrantLock();

    // Constructor
    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    // Deposit method
   public synchronized void deposit(double amount) {

    lock.lock();

    try {
        balance += amount;

        System.out.println(
            "Deposited KSh " + amount
        );

    } finally {
        lock.unlock();
    }
}

    // Withdrawal method
   public synchronized boolean withdraw(double amount) {

    lock.lock();

    try {

        if (amount <= 0 || balance < amount) {
            System.out.println("Insufficient funds!");
            return false;
        }

        balance -= amount;

        System.out.println(
            "Withdrawn KSh " + amount
        );

        return true;

    } finally {
        lock.unlock();
    }
}
    // Money transfer method
   private static final ReentrantLock transferLock =
    new ReentrantLock();

public boolean transferTo(
    BankAccount destination, double amount) {

    transferLock.lock();

    try {

        lock.lock();

        try {

            if (amount <= 0 || balance < amount) {
                return false;
            }

            destination.lock.lock();

            try {

                balance -= amount;
                destination.balance += amount;

                System.out.println(
                    "Transfer successful: KSh " + amount
                );

                return true;

            } finally {
                destination.lock.unlock();
            }

        } finally {
            lock.unlock();
        }

    } finally {
        transferLock.unlock();
    }
}

    // Balance inquiry method
    public synchronized double getBalance() {

    lock.lock();

    try {
        return balance;

    } finally {
        lock.unlock();
    }
}
