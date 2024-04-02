import java.io.Serializable;
import java.util.ArrayList;

public abstract class User implements Serializable {
    //functions for all user classes
    public abstract void DebitAccount(double amount,Account account);
    public abstract ArrayList<Account> getAccounts();
    public abstract int getSSN();

}
