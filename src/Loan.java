import java.util.Date;

public class Loan {
    protected int ssn;
    protected int accountnumber;
    protected double balance;
    protected double interestrate;
    protected Date datepaymentdue;
    protected Date notifiedofpayment;
    protected double paymentamountdue;
    protected Date lastpaymentdate;
    protected boolean missedpayment;
    protected String collateral;
    protected String accounttype;

    public Loan(int ssn,int accountnumber, double balance, double interestrate, Date datepaymentdue, Date notifiedofpayment, double paymentamountdue, Date lastpaymentdate, boolean missedpayment, String collateral, String accounttype) {
        this.ssn = ssn;
        this.accountnumber = accountnumber;
        this.balance = balance;
        this.interestrate = interestrate;
        this.datepaymentdue = datepaymentdue;
        this.notifiedofpayment = notifiedofpayment;
        this.paymentamountdue = paymentamountdue;
        this.lastpaymentdate = lastpaymentdate;
        this.missedpayment = missedpayment;
        this.collateral = collateral;
        this.accounttype = accounttype;
    }

}
