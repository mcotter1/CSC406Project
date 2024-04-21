import java.io.Serializable;

/**
 * The abstract class for all accounts
 */
public abstract class Account implements Serializable {
    public abstract double getBalance();
    public abstract double setBalance(double newbalance);
    public abstract String getAccounttype();
    public abstract int getSsn();
    public abstract String toString();
    public abstract void AddTransaction(Transaction transaction);
}
