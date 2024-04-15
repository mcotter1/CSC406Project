import java.io.Serializable;
import java.time.LocalDate;

public class Transaction implements Serializable {
    protected String transactiontype;
    protected String accounttype;
    protected double amount;
    protected LocalDate dateoccurred;
    protected double newbalance;
    public Transaction(String transactiontype,String accounttype,double amount,LocalDate dateoccurred,double newbalance){
        this.transactiontype = transactiontype;
        this.accounttype = accounttype;
        this.amount = amount;
        this.dateoccurred = dateoccurred;
        this.newbalance = newbalance;
    }

    public String getTransactiontype() {
        return transactiontype;
    }

    public void setTransactiontype(String transactiontype) {
        this.transactiontype = transactiontype;
    }

    public String getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(String accounttype) {
        this.accounttype = accounttype;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDateoccurred() {
        return dateoccurred;
    }

    public void setDateoccurred(LocalDate dateoccurred) {
        this.dateoccurred = dateoccurred;
    }

    public double getNewbalance() {
        return newbalance;
    }

    public void setNewbalance(double newbalance) {
        this.newbalance = newbalance;
    }
}
