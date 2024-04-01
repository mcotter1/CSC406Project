import java.io.Serializable;

public abstract class Account implements Serializable {
    protected int ssn;
    public abstract double getBalance();
    public abstract double setBalance(double newbalance);
    public abstract String getAccounttype();
}
