import java.io.Serializable;
import java.util.Date;

public class Transaction implements Serializable {
    protected String transactiontype;
    protected String accounttype;
    protected double amount;
    protected Date dateoccurred;
    public Transaction(String transactiontype,String accounttype,double amount,Date dateoccurred){
        this.transactiontype = transactiontype;
        this.accounttype = accounttype;
        this.amount = amount;
        this.dateoccurred = dateoccurred;
    }
}
