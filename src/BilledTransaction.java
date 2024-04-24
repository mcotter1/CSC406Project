import java.time.LocalDate;

public class BilledTransaction extends Transaction {
    private boolean isBilled = false;

    public BilledTransaction(String transactiontype,String accounttype,double amount,LocalDate dateoccurred,double newbalance, boolean isBilled) {
        super(transactiontype, accounttype, amount, dateoccurred, newbalance);
        this.isBilled = isBilled;
    }

    public boolean isBilled() {
        return isBilled;
    }

    public void setBilled(boolean isBilled) {
        this.isBilled = isBilled;
    }
}