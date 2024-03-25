import java.io.Serializable;

public abstract class User implements Serializable {
    //functions for all user classes
    public abstract void DebitAccount(double amount,Account account);
}
