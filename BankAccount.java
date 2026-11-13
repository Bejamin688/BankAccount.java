
public class BankAccount {

    private double balance;

    // Constructor
    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    // Deposit method
    public synchronized void deposit(double amount) {
        if (amount > 0) {
            balance += amount;

            System.out.println(
                "Deposited: KSh " + amount
            );
        }
    }

    // Withdrawal method
    public synchronized boolean withdraw(double amount) {

        if (amount <= 0) {
            return false;
        }

        if (balance >= amount) {
            balance -= amount;

            System.out.println(
                "Withdrawn: KSh " + amount
            );

            return true;
        }

        System.out.println("Insufficient funds!");

        return false;
    }

    // Money transfer method
    public boolean transferTo(
        BankAccount destination, double amount) {

        synchronized (this) {

            if (balance < amount || amount <= 0) {
                System.out.println("Transfer failed!");
                return false;
            }

            synchronized (destination) {

                balance -= amount;
                destination.balance += amount;

                System.out.println(
                    "Transferred: KSh " + amount
                );

                return true;
            }
        }
    }

    // Balance inquiry method
    public synchronized double getBalance() {
        return balance;
    }
}
